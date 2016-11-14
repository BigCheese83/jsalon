package ru.bigcheese.jsalon.dao.mapper;

import ru.bigcheese.jsalon.model.ScheduleEntry;
import ru.bigcheese.jsalon.model.enums.ScheduleEntryStatus;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class ScheduleMapper extends AbstractRowMapper<ScheduleEntry> {

    private final MasterMapper masterMapper;
    private final ServiceMapper serviceMapper;

    public ScheduleMapper() {
        Map<String, String> smap = new HashMap<>();
        smap.put("id", "serv_id");
        smap.put("created", "serv_created");
        smap.put("modified", "serv_modified");
        smap.put("version", "serv_version");
        smap.put("name", "serv_name");
        smap.put("cost", "cost");
        smap.put("duration", "duration");
        smap.put("description", "serv_description");
        serviceMapper = new ServiceMapper(smap);

        Map<String, String> mmap = new HashMap<>();
        mmap.put("id", "m_id");
        mmap.put("created", "m_created");
        mmap.put("modified", "m_modified");
        mmap.put("version", "m_version");
        mmap.put("hiring_date", "m_hiring_date");
        Map<String, String> pmap = new HashMap<>();
        pmap.put("id", "p_id");
        pmap.put("created", "p_created");
        pmap.put("modified", "p_modified");
        pmap.put("version", "p_version");
        pmap.put("surname", "m_surname");
        pmap.put("name", "m_name");
        pmap.put("patronymic", "m_patronymic");
        pmap.put("birth_date", "m_birth_date");
        Map<String, String> cmap = new HashMap<>();
        cmap.put("id", "c_id");
        cmap.put("created", "c_created");
        cmap.put("modified", "c_modified");
        cmap.put("version", "c_version");
        cmap.put("phone", "m_phone");
        cmap.put("email", "m_email");
        cmap.put("vk", "m_vk");
        cmap.put("skype", "m_skype");
        cmap.put("facebook", "m_facebook");
        cmap.put("twitter", "m_twitter");
        cmap.put("icq", "m_icq");
        masterMapper = new MasterMapper(mmap, pmap, cmap);
    }

    public ScheduleMapper(Map<String, String> scheduleAlias, Map<String, String> serviceAlias,
                          Map<String, String> masterAlias, Map<String, String> personAlias, Map<String, String> contactAlias) {
        super(scheduleAlias);
        masterMapper = new MasterMapper(masterAlias, personAlias, contactAlias);
        serviceMapper = new ServiceMapper(serviceAlias);
    }

    @Override
    public ScheduleEntry mapRow(ResultSet rs) throws SQLException {
        ScheduleEntry schedule = new ScheduleEntry();
        schedule.setId(rs.getLong(get("id")));
        schedule.setCreated(toDateTime(rs.getTimestamp(get("created"))));
        schedule.setModified(toDateTime(rs.getTimestamp(get("modified"))));
        schedule.setVersion(rs.getInt(get("version")));
        schedule.setRegDate(toDate(rs.getDate(get("reg_date"))));
        schedule.setRegTime(toTime(rs.getTime(get("reg_time"))));
        schedule.setSurname(rs.getString(get("surname")));
        schedule.setName(rs.getString(get("name")));
        schedule.setPhone(rs.getString(get("phone")));
        String status = rs.getString(get("status"));
        schedule.setStatus(status != null ? ScheduleEntryStatus.valueOf(status) : null);
        schedule.setNote(rs.getString("note"));
        long masterId = rs.getLong(masterMapper.get("id"));
        if (masterId > 0) {
            schedule.setMaster(masterMapper.mapRow(rs));
        }
        long serviceId = rs.getLong(serviceMapper.get("id"));
        if (serviceId > 0) {
            schedule.setService(serviceMapper.mapRow(rs));
        }
        return schedule;
    }
}
