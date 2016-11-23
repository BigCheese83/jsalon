package ru.bigcheese.jsalon.dao;

import com.google.common.collect.ImmutableMap;
import ru.bigcheese.jsalon.core.exception.OptimisticLockException;
import ru.bigcheese.jsalon.dao.mapper.GroupMapper;
import ru.bigcheese.jsalon.dao.mapper.RowMapper;
import ru.bigcheese.jsalon.model.Group;

import javax.ejb.Stateless;
import java.util.Map;

@Stateless
public class GroupDaoBean extends BaseDaoBean<Group> implements GroupDao {

    private static final Map<String, String> queries = new ImmutableMap.Builder<String, String>()
            .put(INSERT_KEY,
                    "insert into groups (id, created, modified, version, groupname, description) values (?, now(), now(), 0, ?, ?)")
            .put(UPDATE_KEY,
                    "update groups set modified = now(), version = version + 1, groupname = ?, description = ? " +
                    "where id = ? and version = ?")
            .put(DELETE_KEY, "delete from groups where id = ?")
            .put(FIND_ALL_KEY, "select * from groups order by modified")
            .put(FIND_ALL_LIMIT_KEY, "select * from groups order by modified limit ? offset ?")
            .put(FIND_BY_ID_KEY, "select * from groups where id = ?")
            .put(COUNT_KEY, "select count(*) from groups")
            .put(EXISTS_BY_ID_KEY, "select id from groups where id = ?")
            .build();

    private static final RowMapper<Group> mapper = new GroupMapper();

    @Override
    public void create(Group model) {
        Long id = generateId("select nextval('groups_id_seq')");
        executeUpdate(getSql(INSERT_KEY), id,
                getParam(model.getName(), String.class),
                getParam(model.getDescription(), String.class));
        model.setId(id);
    }

    @Override
    public void update(Group model) {
        int upd = executeUpdate(getSql(UPDATE_KEY),
                getParam(model.getName(), String.class),
                getParam(model.getDescription(), String.class),
                model.getId(), model.getVersion());
        if (upd == 0) {
            throw new OptimisticLockException("Update failed. " +
                    "Group with id =" + model.getId() + "has already been updated in another transaction.");
        }
    }

    @Override
    protected String getSql(String key) {
        return queries.get(key);
    }

    @Override
    protected RowMapper<Group> getMapper() {
        return mapper;
    }
}
