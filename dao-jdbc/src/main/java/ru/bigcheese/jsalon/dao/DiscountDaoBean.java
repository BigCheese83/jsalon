package ru.bigcheese.jsalon.dao;

import com.google.common.collect.ImmutableMap;
import ru.bigcheese.jsalon.core.exception.DatabaseException;
import ru.bigcheese.jsalon.core.exception.OptimisticLockException;
import ru.bigcheese.jsalon.dao.mapper.DiscountMapper;
import ru.bigcheese.jsalon.dao.mapper.RowMapper;
import ru.bigcheese.jsalon.model.Discount;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Map;
import java.util.Set;

@Stateless
public class DiscountDaoBean extends BaseDaoBean<Discount> implements DiscountDao {

    private static final Map<String, String> queries = new ImmutableMap.Builder<String, String>()
            .put(INSERT_KEY,
                    "insert into discount (id, created, modified, version, name, value, description) " +
                            "values (?, now(), now(), 0, ?, ?, ?)")
            .put(UPDATE_KEY,
                    "update discount set modified = now(), version = version + 1, " +
                    "name = ?, value = ?, description = ? " +
                    "where id = ? and version = ?")
            .put(DELETE_KEY, "delete from discount where id = ?")
            .put(FIND_ALL_KEY, "select * from discount order by modified")
            .put(FIND_ALL_LIMIT_KEY, "select * from discount order by modified limit ? offset ?")
            .put(FIND_BY_ID_KEY, "select * from discount where id = ?")
            .put(COUNT_KEY, "select count(*) from discount")
            .put(EXISTS_BY_ID_KEY, "select id from discount where id = ?")
            .build();

    private static final RowMapper<Discount> mapper = new DiscountMapper();


    @Override
    public void create(Discount model) {
        Long id = generateId("select nextval('discount_id_seq')");
        executeUpdate(getSql(INSERT_KEY), id,
                getParam(model.getName(), String.class),
                getParam(model.getValue(), Integer.class),
                getParam(model.getDescription(), String.class));
        model.setId(id);
    }

    @Override
    public void update(Discount model) {
        int upd = executeUpdate(getSql(UPDATE_KEY),
                getParam(model.getName(), String.class),
                getParam(model.getValue(), Integer.class),
                getParam(model.getDescription(), String.class),
                model.getId(), model.getVersion());
        if (upd == 0) {
            throw new OptimisticLockException("Update failed. " +
                    "Discount with id =" + model.getId() + "has already been updated in another transaction.");
        }
    }

    @TransactionAttribute(TransactionAttributeType.SUPPORTS)
    @Override
    public boolean existByName(String name) {
        String sql = "select name from discount where name = ?";
        return executeSingleResultQuery(sql, String.class, name) != null;
    }

    @Override
    public void deleteDiscounts(Set<Long> ids) {
        String sql = "delete from discount where id = any(?)";
        try (Connection connection = getDataSource().getConnection();
             PreparedStatement pstm = connection.prepareStatement(sql)) {
            pstm.setArray(1, connection.createArrayOf("BIGINT", ids.toArray()));
            pstm.executeUpdate();
        } catch (SQLException e) {
            throw new DatabaseException(e);
        }
    }

    @Override
    protected RowMapper<Discount> getMapper() {
        return mapper;
    }

    @Override
    protected String getSql(String key) {
        return queries.get(key);
    }
}
