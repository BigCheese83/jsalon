package ru.bigcheese.jsalon.dao;

import com.google.common.collect.ImmutableMap;
import ru.bigcheese.jsalon.core.exception.OptimisticLockException;
import ru.bigcheese.jsalon.dao.mapper.MasterMapper;
import ru.bigcheese.jsalon.dao.mapper.RowMapper;
import ru.bigcheese.jsalon.model.Master;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.time.LocalDate;
import java.util.Map;

@Stateless
public class MasterDaoBean extends BaseDaoBean<Master> implements MasterDao {

    private static final String BASE_SELECT = "select" +
            "   m.id, m.created, m.modified, m.version, m.hiring_date," +
            "   p.id as p_id, p.created as p_created, p.modified as p_modified, p.version as p_version, p.surname, p.name, p.patronymic, p.birth_date," +
            "   c.id as c_id, c.created as c_created, c.modified as c_modified, c.version as c_version, c.phone, c.email, c.vk, c.skype, c.facebook, c.twitter, c.icq " +
            "from master m" +
            "   join person p on m.person_id = p.id" +
            "   join contact c on p.contact_id = c.id";

    private static final Map<String, String> queries = new ImmutableMap.Builder<String, String>()
            .put(INSERT_KEY,
                    "insert into master (id, created, modified, version, person_id, hiring_date, post_id) " +
                    "values (?, now(), now(), 0, ?, ?, ?)")
            .put(UPDATE_KEY,
                    "update master set modified = now(), version = version + 1, " +
                    "   hiring_date = ?, post_id = ? " +
                    "where id = ? and version = ?")
            .put(DELETE_KEY, "delete from master where id = ?")
            .put(FIND_ALL_KEY, BASE_SELECT + " order by m.modified")
            .put(FIND_ALL_LIMIT_KEY, BASE_SELECT + " order by m.modified limit ? offset ?")
            .put(FIND_BY_ID_KEY, BASE_SELECT + " where m.id = ?")
            .put(COUNT_KEY, "select count(*) from master")
            .put(EXISTS_BY_ID_KEY, "select id from master where id = ?")
            .build();

    private static final RowMapper<Master> mapper = new MasterMapper();

    @Inject
    private PersonDao personDao;

    @Override
    public void create(Master model) {
        Long personId = cascadeCreate(model.getPerson(), personDao);
        Long id = generateId("select nextval('master_id_seq')");
        executeUpdate(getSql(INSERT_KEY), id,
                getParam(personId, Long.class),
                getParam(model.getHiringDate(), LocalDate.class),
                getParam(model.getPost() != null ? model.getPost().getId() : null, Long.class));
        model.setId(id);
    }

    @Override
    public void update(Master model) {
        int upd = executeUpdate(getSql(UPDATE_KEY),
                getParam(model.getHiringDate(), LocalDate.class),
                getParam(model.getPost() != null ? model.getPost().getId() : null, Long.class),
                model.getId(), model.getVersion());
        if (upd == 0) {
            throw new OptimisticLockException("Update failed. " +
                    "Master with id =" + model.getId() + "has already been updated in another transaction.");
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
    protected RowMapper<Master> getMapper() {
        return mapper;
    }
}
