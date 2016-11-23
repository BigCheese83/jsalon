package ru.bigcheese.jsalon.dao;

import com.google.common.collect.ImmutableMap;
import ru.bigcheese.jsalon.core.exception.OptimisticLockException;
import ru.bigcheese.jsalon.dao.mapper.RowMapper;
import ru.bigcheese.jsalon.dao.mapper.ServiceMapper;
import ru.bigcheese.jsalon.model.Post;
import ru.bigcheese.jsalon.model.Service;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import java.math.BigDecimal;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Stateless
public class ServiceDaoBean extends BaseDaoBean<Service> implements ServiceDao {

    private static final Map<String, String> queries = new ImmutableMap.Builder<String, String>()
            .put(INSERT_KEY,
                    "insert into service (id, created, modified, version, name, cost, duration, description) " +
                    "values (?, now(), now(), 0, ?, ?, ?, ?)")
            .put(UPDATE_KEY,
                    "update service set modified = now(), version = version + 1, " +
                    "name = ?, cost = ?, duration = ?, description = ? " +
                    "where id = ? and version = ?")
            .put(DELETE_KEY, "delete from service where id = ?")
            .put(FIND_ALL_KEY, "select * from service order by modified")
            .put(FIND_ALL_LIMIT_KEY, "select * from service order by modified limit ? offset ?")
            .put(FIND_BY_ID_KEY, "select * from service where id = ?")
            .put(COUNT_KEY, "select count(*) from service")
            .put(EXISTS_BY_ID_KEY, "select id from service where id = ?")
            .build();

    private static final RowMapper<Service> mapper = new ServiceMapper();

    @Override
    public void create(Service model) {
        Long id = generateId("select nextval('service_id_seq')");
        executeUpdate(getSql(INSERT_KEY), id,
                getParam(model.getName(), String.class),
                getParam(model.getCost(), BigDecimal.class),
                getParam(model.getDuration(), Integer.class),
                getParam(model.getDescription(), String.class));
        model.setId(id);

        Set<Post> posts = model.getPosts();
        if (posts != null && posts.size() > 0) {
            String sql = "insert into post_service values (nextval('post_service_id_seq'), ?, ?)";
            Object[][] params = posts.stream()
                    .map(e -> new Object[]{e.getId(), model.getId()})
                    .toArray(Object[][]::new);
            batchUpdate(sql, params);
        }
    }

    @Override
    public void update(Service model) {
        int upd = executeUpdate(getSql(UPDATE_KEY),
                getParam(model.getName(), String.class),
                getParam(model.getCost(), BigDecimal.class),
                getParam(model.getDuration(), Integer.class),
                getParam(model.getDescription(), String.class),
                model.getId(), model.getVersion());
        if (upd == 0) {
            throw new OptimisticLockException("Update failed. " +
                    "Post with id =" + model.getId() + "has already been updated in another transaction.");
        }

        Set<Post> posts = model.getPosts();
        if (posts != null) {
            String deleteSql = "delete from post_service where service_id = ?";
            executeUpdate(deleteSql, model.getId());
            if (posts.size() > 0) {
                String idSet = posts.stream()
                        .map(s -> String.valueOf(s.getId()))
                        .collect(Collectors.joining(","));
                String insertSql =
                        "insert into post_service(id, service_id, post_id)" +
                        "   select nextval('post_service_id_seq'), "+ model.getId() +", a.* " +
                        "   from unnest(array["+ String.join(",", idSet) +"]) a";
                executeUpdate(insertSql);
            }
        }
    }

    @Override
    public void delete(Long id) {
        executeUpdate("delete from post_service where service_id = ?", id);
        super.delete(id);
    }

    @TransactionAttribute(TransactionAttributeType.SUPPORTS)
    @Override
    public boolean existsByName(String name) {
        String sql = "select name from service where name = ?";
        return executeSingleResultQuery(sql, String.class, name) != null;
    }

    @Override
    protected String getSql(String key) {
        return queries.get(key);
    }

    @Override
    protected RowMapper<Service> getMapper() {
        return mapper;
    }
}
