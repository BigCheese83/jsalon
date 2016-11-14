package ru.bigcheese.jsalon.dao;

import com.google.common.collect.ImmutableMap;
import ru.bigcheese.jsalon.dao.exception.OptimisticLockException;
import ru.bigcheese.jsalon.dao.mapper.ClientMapper;
import ru.bigcheese.jsalon.dao.mapper.RowMapper;
import ru.bigcheese.jsalon.model.Client;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.time.LocalDate;
import java.util.Map;

@Stateless
public class ClientDaoBean extends BaseDaoBean<Client> implements ClientDao {

    private static final String BASE_SELECT = "select" +
            "   cl.id, cl.created, cl.modified, cl.version, cl.reg_date, cl.in_blacklist," +
            "   p.id as p_id, p.created as p_created, p.modified as p_modified, p.version as p_version, p.surname, p.name, p.patronymic, p.birth_date," +
            "   c.id as c_id, c.created as c_created, c.modified as c_modified, c.version as c_version, c.phone, c.email, c.vk, c.skype, c.facebook, c.twitter, c.icq " +
            "from client cl" +
            "   join person p on cl.person_id = p.id" +
            "   join contact c on p.contact_id = c.id";

    private static final Map<String, String> queries = new ImmutableMap.Builder<String, String>()
            .put(INSERT_KEY,
                    "insert into client (id, created, modified, version, person_id, reg_date, discount_id, in_blacklist) " +
                    "values (?, now(), now(), 0, ?, ?, ?, ?)")
            .put(UPDATE_KEY,
                    "update client set modified = now(), version = version + 1, " +
                    "   reg_date = ?, discount_id = ?, in_blacklist = ? " +
                    "where id = ? and version = ?")
            .put(DELETE_KEY, "delete from client where id = ?")
            .put(FIND_ALL_KEY, BASE_SELECT + " order by cl.modified")
            .put(FIND_ALL_LIMIT_KEY, BASE_SELECT + " order by cl.modified limit ? offset ?")
            .put(FIND_BY_ID_KEY, BASE_SELECT + " where cl.id = ?")
            .put(COUNT_KEY, "select count(*) from client")
            .put(EXISTS_BY_ID_KEY, "select id from client where id = ?")
            .build();

    private static final RowMapper<Client> mapper = new ClientMapper();

    @Inject
    private PersonDao personDao;

    @Override
    public void create(Client model) {
        Long personId = cascadeCreate(model.getPerson(), personDao);
        Long id = generateId("select nextval('client_id_seq')");
        executeUpdate(getSql(INSERT_KEY), id,
                getParam(personId, Long.class),
                getParam(model.getRegistrationDate(), LocalDate.class),
                getParam(model.getDiscount() != null ? model.getDiscount().getId() : null, Long.class),
                getParam(model.isInBlackList(), Boolean.class));
        model.setId(id);
    }

    @Override
    public void update(Client model) {
        int upd = executeUpdate(getSql(UPDATE_KEY),
                getParam(model.getRegistrationDate(), LocalDate.class),
                getParam(model.getDiscount() != null ? model.getDiscount().getId() : null, Long.class),
                getParam(model.isInBlackList(), Boolean.class),
                model.getId(), model.getVersion());
        if (upd == 0) {
            throw new OptimisticLockException("Update failed. " +
                    "Client with id =" + model.getId() + "has already been updated in another transaction.");
        }
        if (model.getPerson() != null) {
            personDao.update(model.getPerson());
        }
    }

    @Override
    protected String getSql(String key) {
        return queries.get(key);
    }

    @Override
    protected RowMapper<Client> getMapper() {
        return mapper;
    }

}
