package ru.bigcheese.jsalon.dao.mapper;

import ru.bigcheese.jsalon.model.Master;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class MasterMapper extends AbstractRowMapper<Master> {

    private final PersonMapper personMapper;

    public MasterMapper() {
        Map<String, String> pmap = new HashMap<>();
        pmap.put("id", "p_id");
        pmap.put("created", "p_created");
        pmap.put("modified", "p_modified");
        pmap.put("version", "p_version");
        pmap.put("surname", "surname");
        pmap.put("name", "name");
        pmap.put("patronymic", "patronymic");
        pmap.put("birth_date", "birth_date");
        Map<String, String> cmap = new HashMap<>();
        cmap.put("id", "c_id");
        cmap.put("created", "c_created");
        cmap.put("modified", "c_modified");
        cmap.put("version", "c_version");
        cmap.put("phone", "phone");
        cmap.put("email", "email");
        cmap.put("vk", "vk");
        cmap.put("skype", "skype");
        cmap.put("facebook", "facebook");
        cmap.put("twitter", "twitter");
        cmap.put("icq", "icq");
        personMapper = new PersonMapper(pmap, cmap);
    }

    public MasterMapper(Map<String, String> masterAlias, Map<String, String> personAlias,
                        Map<String, String> contactAlias) {
        super(masterAlias);
        personMapper = new PersonMapper(personAlias, contactAlias);
    }

    @Override
    public Master mapRow(ResultSet rs) throws SQLException {
        Master master = new Master();
        master.setId(rs.getLong(get("id")));
        master.setCreated(toDateTime(rs.getTimestamp(get("created"))));
        master.setModified(toDateTime(rs.getTimestamp(get("modified"))));
        master.setVersion(rs.getInt(get("version")));
        master.setHiringDate(toDate(rs.getDate(get("hiring_date"))));
        long personId = rs.getLong(personMapper.get("id"));
        if (personId > 0) {
            master.setPerson(personMapper.mapRow(rs));
        }
        return master;
    }
}
