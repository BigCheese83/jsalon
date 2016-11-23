package ru.bigcheese.jsalon.dao;

import com.google.common.collect.ImmutableMap;
import ru.bigcheese.jsalon.core.exception.OptimisticLockException;
import ru.bigcheese.jsalon.dao.mapper.ContactMapper;
import ru.bigcheese.jsalon.dao.mapper.RowMapper;
import ru.bigcheese.jsalon.model.Contact;

import javax.ejb.Stateless;
import java.util.Map;

@Stateless
public class ContactDaoBean extends BaseDaoBean<Contact> implements ContactDao {

    private static final Map<String, String> queries = new ImmutableMap.Builder<String, String>()
            .put(INSERT_KEY,
                    "insert into contact (id, created, modified, version, phone, email, vk, skype, facebook, twitter, icq) " +
                    "values (?, now(), now(), 0, ?, ?, ?, ?, ?, ?, ?)")
            .put(UPDATE_KEY,
                    "update contact set modified = now(), version = version + 1, " +
                    "phone = ?, email = ?, vk = ?, skype = ?, facebook = ?, twitter = ?, icq = ? " +
                    "where id = ? and version = ?")
            .put(DELETE_KEY, "delete from contact where id = ?")
            .put(FIND_ALL_KEY, "select * from contact order by modified")
            .put(FIND_ALL_LIMIT_KEY, "select * from contact order by modified limit ? offset ?")
            .put(FIND_BY_ID_KEY, "select * from contact where id = ?")
            .put(COUNT_KEY, "select count(*) from contact")
            .put(EXISTS_BY_ID_KEY, "select id from contact where id = ?")
            .build();

    private static final RowMapper<Contact> mapper = new ContactMapper();

    @Override
    public void create(Contact model) {
        Long id = generateId("select nextval('contact_id_seq')");
        executeUpdate(getSql(INSERT_KEY), id,
                getParam(model.getPhone(), String.class),
                getParam(model.getEmail(), String.class),
                getParam(model.getVk(), String.class),
                getParam(model.getSkype(), String.class),
                getParam(model.getFacebook(), String.class),
                getParam(model.getTwitter(), String.class),
                getParam(model.getIcq(), String.class));
        model.setId(id);
    }

    @Override
    public void update(Contact model) {
        int upd = executeUpdate(getSql(UPDATE_KEY),
                getParam(model.getPhone(), String.class),
                getParam(model.getEmail(), String.class),
                getParam(model.getVk(), String.class),
                getParam(model.getSkype(), String.class),
                getParam(model.getFacebook(), String.class),
                getParam(model.getTwitter(), String.class),
                getParam(model.getIcq(), String.class),
                model.getId(), model.getVersion());
        if (upd == 0) {
            throw new OptimisticLockException("Update failed. " +
                    "Contact with id =" + model.getId() + "has already been updated in another transaction.");
        }
    }

    @Override
    protected RowMapper<Contact> getMapper() {
        return mapper;
    }

    @Override
    protected String getSql(String key) {
        return queries.get(key);
    }
}
