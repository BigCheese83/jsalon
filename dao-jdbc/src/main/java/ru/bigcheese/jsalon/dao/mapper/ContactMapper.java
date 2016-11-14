package ru.bigcheese.jsalon.dao.mapper;

import ru.bigcheese.jsalon.model.Contact;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;

public class ContactMapper extends AbstractRowMapper<Contact> {

    public ContactMapper() {}

    public ContactMapper(Map<String, String> mapAlias) {
        super(mapAlias);
    }

    @Override
    public Contact mapRow(ResultSet rs) throws SQLException {
        Contact contact = new Contact();
        contact.setId(rs.getLong(get("id")));
        contact.setCreated(toDateTime(rs.getTimestamp(get("created"))));
        contact.setModified(toDateTime(rs.getTimestamp(get("modified"))));
        contact.setVersion(rs.getInt(get("version")));
        contact.setPhone(rs.getString(get("phone")));
        contact.setEmail(rs.getString(get("email")));
        contact.setVk(rs.getString(get("vk")));
        contact.setSkype(rs.getString(get("skype")));
        contact.setFacebook(rs.getString(get("facebook")));
        contact.setTwitter(rs.getString(get("twitter")));
        contact.setIcq(rs.getString(get("icq")));
        return contact;
    }
}
