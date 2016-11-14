package ru.bigcheese.jsalon.dao;

import com.google.common.collect.ImmutableMap;
import ru.bigcheese.jsalon.dao.exception.DatabaseException;
import ru.bigcheese.jsalon.dao.exception.OptimisticLockException;
import ru.bigcheese.jsalon.dao.mapper.PersonMapper;
import ru.bigcheese.jsalon.dao.mapper.RowMapper;
import ru.bigcheese.jsalon.model.Contact;
import ru.bigcheese.jsalon.model.Person;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Map;

@Stateless
public class PersonDaoBean extends BaseDaoBean<Person> implements PersonDao {

    private static final String BASE_SELECT = "select " +
            "  p.id, p.created, p.modified, p.version, p.surname, p.name, p.patronymic, p.birth_date," +
            "  c.id as c_id, c.created as c_created, c.modified as c_modified, c.version as c_version, c.phone, c.email, c.vk, c.skype, c.facebook, c.twitter, c.icq " +
            "from person p join contact c on p.contact_id = c.id";

    private static final Map<String, String> queries = new ImmutableMap.Builder<String, String>()
            .put(INSERT_KEY,
                    "insert into person (id, created, modified, version, surname, name, patronymic, birth_date," +
                    "   passport_id, reg_address_id, live_address_id, contact_id) " +
                    "values (?, now(), now(), 0, ?, ?, ?, ?, ?, ?, ?, ?)")
            .put(UPDATE_KEY,
                    "update person set modified = now(), version = version + 1, " +
                    "   surname = ?, name = ?, patronymic = ?, birth_date = ?, " +
                    "   passport_id = ?, reg_address_id = ?, live_address_id = ?, contact_id = ?" +
                    "where id = ? and version = ?")
            .put(DELETE_KEY, "delete from person where id = ?")
            .put(FIND_ALL_KEY, BASE_SELECT + " order by p.modified")
            .put(FIND_ALL_LIMIT_KEY, BASE_SELECT + " order by p.modified limit ? offset ?")
            .put(FIND_BY_ID_KEY, BASE_SELECT + " where p.id = ?")
            .put(COUNT_KEY, "select count(*) from person")
            .put(EXISTS_BY_ID_KEY, "select id from person where id = ?")
            .build();

    private static final RowMapper<Person> mapper = new PersonMapper();

    @Inject
    private PassportDao passportDao;
    @Inject
    private AddressDao addressDao;
    @Inject
    private ContactDao contactDao;

    @Override
    public void create(Person model) {
        Long passportId = cascadeCreate(model.getPassport(), passportDao);
        Long regAddressId = cascadeCreate(model.getRegAddress(), addressDao);
        Long liveAddressId = cascadeCreate(model.getLiveAddress(), addressDao);
        Long contactId = cascadeCreate(model.getContact(), contactDao);

        Long id = generateId("select nextval('person_id_seq')");
        executeUpdate(getSql(INSERT_KEY), id,
                getParam(model.getSurname(), String.class),
                getParam(model.getName(), String.class),
                getParam(model.getPatronymic(), String.class),
                getParam(model.getBirthDate(), LocalDate.class),
                getParam(passportId, Long.class),
                getParam(regAddressId, Long.class),
                getParam(liveAddressId, Long.class),
                getParam(contactId, Long.class));
        model.setId(id);
    }

    @Override
    public void update(Person model) {
        int upd = executeUpdate(getSql(UPDATE_KEY),
                getParam(model.getSurname(), String.class),
                getParam(model.getName(), String.class),
                getParam(model.getPatronymic(), String.class),
                getParam(model.getBirthDate(), LocalDate.class),
                getParam(model.getPassport() != null ? model.getPassport().getId() : null, Long.class),
                getParam(model.getRegAddress() != null ? model.getRegAddress().getId() : null, Long.class),
                getParam(model.getLiveAddress() != null ? model.getLiveAddress().getId() : null, Long.class),
                getParam(model.getContact() != null ? model.getContact().getId() : null, Long.class),
                model.getId(), model.getVersion());
        if (upd == 0) {
            throw new OptimisticLockException("Update failed. " +
                    "Person with id =" + model.getId() + "has already been updated in another transaction.");
        }
        if (model.getPassport() != null) {
            passportDao.update(model.getPassport());
        }
        if (model.getRegAddress() != null) {
            addressDao.update(model.getRegAddress());
        }
        if (model.getLiveAddress() != null) {
            addressDao.update(model.getLiveAddress());
        }
        if (model.getContact() != null) {
            contactDao.update(model.getContact());
        }
    }

    @Override
    public void delete(Long id) {
        String sql = "select passport_id, reg_address_id, live_address_id, contact_id from person where id = ?";
        try (Connection conn = getDataSource().getConnection();
             PreparedStatement pstm = conn.prepareStatement(sql)) {
            setParameters(pstm, id);
            try (ResultSet rs = pstm.executeQuery()) {
                if (rs.next()) {
                    super.delete(id);
                    long passportId = rs.getLong("passport_id");
                    long regAddrId = rs.getLong("reg_address_id");
                    long liveAddrId = rs.getLong("live_address_id");
                    long contactId = rs.getLong("contact_id");
                    if (passportId > 0) passportDao.delete(passportId);
                    if (regAddrId > 0) addressDao.delete(regAddrId);
                    if (liveAddrId > 0) addressDao.delete(liveAddrId);
                    if (contactId > 0) contactDao.delete(contactId);
                }
            }
        } catch (SQLException e) {
            throw new DatabaseException(e);
        }
    }

    @Override
    protected RowMapper<Person> getMapper() {
        return mapper;
    }

    @Override
    protected String getSql(String key) {
        return queries.get(key);
    }
}
