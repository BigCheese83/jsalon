package ru.bigcheese.jsalon.dao;

import com.google.common.collect.ImmutableMap;
import ru.bigcheese.jsalon.dao.exception.OptimisticLockException;
import ru.bigcheese.jsalon.dao.mapper.RowMapper;
import ru.bigcheese.jsalon.dao.mapper.ScheduleMapper;
import ru.bigcheese.jsalon.model.ScheduleEntry;

import javax.ejb.Stateless;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Map;

@Stateless
public class ScheduleDaoBean extends BaseDaoBean<ScheduleEntry> implements ScheduleDao {

    private static final String BASE_SELECT = "select " +
            "   s.id, s.created, s.modified, s.version, s.reg_date, s.reg_time, s.surname, s.name, s.phone, s.status, s.note," +
            "   m.id as m_id, m.created as m_created, m.modified as m_modified, m.version as m_version, m.hiring_date as m_hiring_date, " +
            "   p.id as p_id, p.created as p_created, p.modified as p_modified, p.version as p_version, p.surname as m_surname, p.name as m_name, p.patronymic as m_patronymic, p.birth_date as m_birth_date," +
            "   c.id as c_id, c.created as c_created, c.modified as c_modified, c.version as c_version, c.phone as m_phone, c.email as m_email, c.vk as m_vk, c.skype as m_skype, c.facebook as m_facebook, c.twitter as m_twitter, c.icq as m_icq," +
            "   serv.id as serv_id, serv.created as serv_created, serv.modified as serv_modified, serv.version as serv_version, serv.name as serv_name, serv.cost, serv.duration, serv.description as serv_description " +
            "from schedule s" +
            "   left join master m on s.master_id = m.id" +
            "   left join person p on m.person_id = p.id" +
            "   left join contact c on p.contact_id = c.id" +
            "   left join service serv on s.service_id = serv.id";

    private static final Map<String, String> queries = new ImmutableMap.Builder<String, String>()
            .put(INSERT_KEY,
                    "insert into schedule (id, created, modified, version, reg_date, reg_time," +
                    "   surname, name, phone, master_id, service_id, status, note) " +
                    "values (?, now(), now(), 0, ?, ?, ?, ?, ?, ?, ?, ?, ?)")
            .put(UPDATE_KEY,
                    "update schedule set modified = now(), version = version + 1, " +
                    "   reg_date = ?, reg_time = ?, surname = ?, name = ?, phone = ?, master_id = ?, service_id = ?, status = ?, note = ? " +
                    "where id = ? and version = ?")
            .put(DELETE_KEY, "delete from schedule where id = ?")
            .put(FIND_ALL_KEY, BASE_SELECT + " order by s.modified")
            .put(FIND_ALL_LIMIT_KEY, BASE_SELECT + " order by s.modified limit ? offset ?")
            .put(FIND_BY_ID_KEY, BASE_SELECT + " where s.id = ?")
            .put(COUNT_KEY, "select count(*) from schedule")
            .put(EXISTS_BY_ID_KEY, "select id from schedule where id = ?")
            .build();

    private static final RowMapper<ScheduleEntry> mapper = new ScheduleMapper();

    @Override
    public void create(ScheduleEntry model) {
        Long id = generateId("select nextval('schedule_id_seq')");
        executeUpdate(getSql(INSERT_KEY), id,
                getParam(model.getRegDate(), LocalDate.class),
                getParam(model.getRegTime(), LocalTime.class),
                getParam(model.getSurname(), String.class),
                getParam(model.getName(), String.class),
                getParam(model.getPhone(), String.class),
                getParam(model.getMaster() != null ? model.getMaster().getId() : null, Long.class),
                getParam(model.getService() != null ? model.getService().getId() : null, Long.class),
                getParam(model.getStatus() != null ? model.getStatus().name() : null, String.class),
                getParam(model.getNote(), String.class));
        model.setId(id);
    }

    @Override
    public void update(ScheduleEntry model) {
        int upd = executeUpdate(getSql(UPDATE_KEY),
                getParam(model.getRegDate(), LocalDate.class),
                getParam(model.getRegTime(), LocalTime.class),
                getParam(model.getSurname(), String.class),
                getParam(model.getName(), String.class),
                getParam(model.getPhone(), String.class),
                getParam(model.getMaster() != null ? model.getMaster().getId() : null, Long.class),
                getParam(model.getService() != null ? model.getService().getId() : null, Long.class),
                getParam(model.getStatus() != null ? model.getStatus().name() : null, String.class),
                getParam(model.getNote(), String.class),
                model.getId(), model.getVersion());
        if (upd == 0) {
            throw new OptimisticLockException("Update failed. " +
                    "Schedule Entry with id =" + model.getId() + "has already been updated in another transaction.");
        }
    }

    @Override
    protected String getSql(String key) {
        return queries.get(key);
    }

    @Override
    protected RowMapper<ScheduleEntry> getMapper() {
        return mapper;
    }
}
