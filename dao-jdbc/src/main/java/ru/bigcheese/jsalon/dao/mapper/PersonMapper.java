package ru.bigcheese.jsalon.dao.mapper;

import ru.bigcheese.jsalon.model.Person;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class PersonMapper extends AbstractRowMapper<Person> {

    private final ContactMapper contactMapper;

    public PersonMapper() {
        Map<String, String> map = new HashMap<>();
        map.put("id", "c_id");
        map.put("created", "c_created");
        map.put("modified", "c_modified");
        map.put("version", "c_version");
        map.put("phone", "phone");
        map.put("email", "email");
        map.put("vk", "vk");
        map.put("skype", "skype");
        map.put("facebook", "facebook");
        map.put("twitter", "twitter");
        map.put("icq", "icq");
        contactMapper = new ContactMapper(map);
    }

    public PersonMapper(Map<String, String> personAlias, Map<String, String> contactAlias) {
        super(personAlias);
        contactMapper = new ContactMapper(contactAlias);
    }

    @Override
    public Person mapRow(ResultSet rs) throws SQLException {
        Person person = new Person();
        person.setId(rs.getLong(get("id")));
        person.setCreated(toDateTime(rs.getTimestamp(get("created"))));
        person.setModified(toDateTime(rs.getTimestamp(get("modified"))));
        person.setVersion(rs.getInt(get("version")));
        person.setSurname(rs.getString(get("surname")));
        person.setName(rs.getString(get("name")));
        person.setPatronymic(rs.getString(get("patronymic")));
        person.setBirthDate(toDate(rs.getDate(get("birth_date"))));
        long contactId = rs.getLong(contactMapper.get("id"));
        if (contactId > 0) {
            person.setContact(contactMapper.mapRow(rs));
        }
        return person;
    }
}
