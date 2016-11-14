package ru.bigcheese.jsalon.dao.mapper;

import ru.bigcheese.jsalon.model.Client;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class ClientMapper extends AbstractRowMapper<Client> {

    private final PersonMapper personMapper;

    public ClientMapper() {
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

    public ClientMapper(Map<String, String> clientAlias, Map<String, String> personAlias,
                        Map<String, String> contactAlias) {
        super(clientAlias);
        personMapper = new PersonMapper(personAlias, contactAlias);
    }

    @Override
    public Client mapRow(ResultSet rs) throws SQLException {
        Client client = new Client();
        client.setId(rs.getLong(get("id")));
        client.setCreated(toDateTime(rs.getTimestamp(get("created"))));
        client.setModified(toDateTime(rs.getTimestamp(get("modified"))));
        client.setVersion(rs.getInt(get("version")));
        client.setRegistrationDate(toDate(rs.getDate(get("reg_date"))));
        client.setInBlackList(rs.getBoolean(get("in_blacklist")));
        long personId = rs.getLong(personMapper.get("id"));
        if (personId > 0) {
            client.setPerson(personMapper.mapRow(rs));
        }
        return client;
    }
}
