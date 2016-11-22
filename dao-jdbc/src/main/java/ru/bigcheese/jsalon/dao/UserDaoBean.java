package ru.bigcheese.jsalon.dao;

import com.google.common.collect.ImmutableMap;
import ru.bigcheese.jsalon.dao.exception.OptimisticLockException;
import ru.bigcheese.jsalon.dao.mapper.RowMapper;
import ru.bigcheese.jsalon.dao.mapper.UserMapper;
import ru.bigcheese.jsalon.model.User;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import java.util.List;
import java.util.Map;

@Stateless
public class UserDaoBean extends BaseDaoBean<User> implements UserDao {

    private static final String BASE_SELECT = "select" +
            "   u.id, u.created, u.modified, u.version, u.username, u.surname, u.name, u.patronymic," +
            "   g.id as g_id, g.created as g_created, g.modified as g_modified, g.version as g_version, g.groupname, g.description as g_description " +
            "from users u" +
            "   join users_groups ug on u.id = ug.user_id" +
            "   join groups g on ug.group_id = g.id";

    private static final Map<String, String> queries = new ImmutableMap.Builder<String, String>()
            .put(INSERT_KEY,
                    "insert into users (id, created, modified, version, username, surname, name, patronymic, password) " +
                    "values (?, now(), now(), 0, ?, ?, ?, ?, md5(?))")
            .put(UPDATE_KEY,
                    "update users set modified = now(), version = version + 1," +
                    "   username = ?, surname = ?, name = ?, patronymic = ? " +
                    "where id = ? and version = ?")
            .put(DELETE_KEY, "delete from users where id = ?")
            .put(FIND_ALL_KEY, BASE_SELECT + " order by u.modified")
            .put(FIND_ALL_LIMIT_KEY, BASE_SELECT + " order by u.modified limit ? offset ?")
            .put(FIND_BY_ID_KEY, BASE_SELECT + " where u.id = ?")
            .put(COUNT_KEY, "select count(*) from users")
            .put(EXISTS_BY_ID_KEY, "select id from users where id = ?")
            .build();

    private static final RowMapper<User> mapper = new UserMapper();

    @Override
    public void create(User model) {
        Long id = generateId("select nextval('users_id_seq')");
        executeUpdate(getSql(INSERT_KEY), id,
                getParam(model.getUsername(), String.class),
                getParam(model.getSurname(), String.class),
                getParam(model.getName(), String.class),
                getParam(model.getPatronymic(), String.class),
                "123321");  //TODO implement default password property
        model.setId(id);

        if (model.getGroup() != null) {
            executeUpdate("insert into users_groups values (?, ?)", id, model.getGroup().getId());
        }
    }

    @Override
    public void update(User model) {
        int upd = executeUpdate(getSql(UPDATE_KEY),
                getParam(model.getUsername(), String.class),
                getParam(model.getSurname(), String.class),
                getParam(model.getName(), String.class),
                getParam(model.getPatronymic(), String.class),
                model.getId(), model.getVersion());
        if (upd == 0) {
            throw new OptimisticLockException("Update failed. " +
                    "User with id =" + model.getId() + "has already been updated in another transaction.");
        }

        if (model.getGroup() != null) {
            executeUpdate("update users_groups set group_id = ? where user_id = ?",
                    model.getGroup().getId(), model.getId());
        }
    }

    @Override
    public void delete(Long id) {
        executeUpdate("delete from users_groups where user_id = ?", id);
        super.delete(id);
    }

    @TransactionAttribute(TransactionAttributeType.SUPPORTS)
    @Override
    public User findByUsername(String username) {
        String sql = BASE_SELECT + " where u.username = ?";
        List<User> find = executeQuery(sql, mapper, getParam(username, String.class));
        return find.isEmpty() ? null : find.get(0);
    }

    @Override
    protected String getSql(String key) {
        return queries.get(key);
    }

    @Override
    protected RowMapper<User> getMapper() {
        return mapper;
    }
}
