package ru.bigcheese.jsalon.dao;

import com.google.common.collect.ImmutableMap;
import ru.bigcheese.jsalon.dao.exception.OptimisticLockException;
import ru.bigcheese.jsalon.dao.mapper.PostMapper;
import ru.bigcheese.jsalon.dao.mapper.RowMapper;
import ru.bigcheese.jsalon.model.Post;
import ru.bigcheese.jsalon.model.Service;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import java.util.*;
import java.util.stream.Collectors;

@Stateless
public class PostDaoBean extends BaseDaoBean<Post> implements PostDao {

    private static final Map<String, String> queries = new ImmutableMap.Builder<String, String>()
            .put(INSERT_KEY,
                    "insert into post (id, created, modified, version, name, description) " +
                    "values (?, now(), now(), 0, ?, ?)")
            .put(UPDATE_KEY,
                    "update post set modified = now(), version = version + 1, name = ?, description = ? " +
                    "where id = ? and version = ?")
            .put(DELETE_KEY, "delete from post where id = ?")
            .put(FIND_ALL_KEY, "select * from post order by modified")
            .put(FIND_ALL_LIMIT_KEY, "select * from post order by modified limit ? offset ?")
            .put(FIND_BY_ID_KEY, "select * from post where id = ?")
            .put(COUNT_KEY, "select count(*) from post")
            .put(EXISTS_BY_ID_KEY, "select id from post where id = ?")
            .build();

    private static final RowMapper<Post> mapper = new PostMapper();

    @Override
    public void create(Post model) {
        Long id = generateId("select nextval('post_id_seq')");
        executeUpdate(getSql(INSERT_KEY), id,
                getParam(model.getName(), String.class),
                getParam(model.getDescription(), String.class));
        model.setId(id);

        Set<Service> services = model.getServices();
        if (services != null && services.size() > 0) {
            String sql = "insert into post_service values (nextval('post_service_id_seq'), ?, ?)";
            Object[][] params = services.stream()
                    .map(e -> new Object[]{model.getId(), e.getId()})
                    .toArray(Object[][]::new);
            batchUpdate(sql, params);
        }
    }

    @Override
    public void update(Post model) {
        int upd = executeUpdate(getSql(UPDATE_KEY),
                getParam(model.getName(), String.class),
                getParam(model.getDescription(), String.class),
                model.getId(), model.getVersion());
        if (upd == 0) {
            throw new OptimisticLockException("Update failed. " +
                    "Post with id =" + model.getId() + "has already been updated in another transaction.");
        }

        Set<Service> services = model.getServices();
        if (services != null) {
            String deleteSql = "delete from post_service where post_id = ?";
            executeUpdate(deleteSql, model.getId());
            if (services.size() > 0) {
                String idSet = services.stream()
                        .map(s -> String.valueOf(s.getId()))
                        .collect(Collectors.joining(","));
                String insertSql =
                        "insert into post_service " +
                        "   select nextval('post_service_id_seq'), "+ model.getId() +", a.* " +
                        "   from unnest(array["+ idSet +"]) a";
                executeUpdate(insertSql);
            }
        }
    }

    @Override
    public void delete(Long id) {
        executeUpdate("delete from post_service where post_id = ?", id);
        super.delete(id);
    }

    @TransactionAttribute(TransactionAttributeType.SUPPORTS)
    @Override
    public boolean existByName(String name) {
        String sql = "select name from post where name = ?";
        return executeSingleResultQuery(sql, String.class, name) != null;
    }

    @Override
    protected String getSql(String key) {
        return queries.get(key);
    }

    @Override
    protected RowMapper<Post> getMapper() {
        return mapper;
    }
}
