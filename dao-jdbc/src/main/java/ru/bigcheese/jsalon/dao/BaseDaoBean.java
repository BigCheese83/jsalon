package ru.bigcheese.jsalon.dao;

import ru.bigcheese.jsalon.core.exception.DatabaseException;
import ru.bigcheese.jsalon.core.paging.Page;
import ru.bigcheese.jsalon.core.paging.PageRequest;
import ru.bigcheese.jsalon.dao.support.NullParam;
import ru.bigcheese.jsalon.dao.mapper.RowMapper;
import ru.bigcheese.jsalon.model.BaseModel;

import javax.annotation.Resource;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.sql.DataSource;
import java.math.BigDecimal;
import java.sql.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public abstract class BaseDaoBean<T extends BaseModel> implements BaseDao<T> {

    protected static final String INSERT_KEY = "insert";
    protected static final String UPDATE_KEY = "update";
    protected static final String DELETE_KEY = "delete";
    protected static final String FIND_ALL_KEY = "find_all";
    protected static final String FIND_ALL_LIMIT_KEY = "find_all_limit";
    protected static final String FIND_BY_ID_KEY = "find_by_id";
    protected static final String COUNT_KEY = "count";
    protected static final String EXISTS_BY_ID_KEY = "exists_by_id";

    @Resource(lookup = "jdbc/salon")
    private DataSource dataSource;

    protected DataSource getDataSource() {
        return dataSource;
    }

    @Override
    public void delete(Long id) {
        executeUpdate(getSql(DELETE_KEY), id);
    }

    @TransactionAttribute(TransactionAttributeType.SUPPORTS)
    @Override
    public List<T> findAll() {
        return executeQuery(getSql(FIND_ALL_KEY), getMapper());
    }

    @TransactionAttribute(TransactionAttributeType.SUPPORTS)
    @Override
    public Page<T> findAll(PageRequest pageRequest) {
        List<T> find = executeQuery(getSql(FIND_ALL_LIMIT_KEY), getMapper(), pageRequest.getPageSize(), pageRequest.getOffset());
        Long count = countAll();
        return new Page<>(find, count, pageRequest.getPageSize());
    }

    @TransactionAttribute(TransactionAttributeType.SUPPORTS)
    @Override
    public T findById(Long id) {
        List<T> find = executeQuery(getSql(FIND_BY_ID_KEY), getMapper(), id);
        return find.isEmpty() ? null : find.get(0);
    }

    @TransactionAttribute(TransactionAttributeType.SUPPORTS)
    @Override
    public Long countAll() {
        return executeSingleResultQuery(getSql(COUNT_KEY), Long.class);
    }

    @TransactionAttribute(TransactionAttributeType.SUPPORTS)
    @Override
    public boolean exists(Long id) {
        return executeSingleResultQuery(getSql(EXISTS_BY_ID_KEY), Long.class, id) != null;
    }

    protected int executeUpdate(String sql, Object... params) {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement pstm = connection.prepareStatement(sql)) {
            setParameters(pstm, params);
            return pstm.executeUpdate();
        } catch (SQLException e) {
            throw new DatabaseException(e);
        }
    }

    protected int[] batchUpdate(String sql, Object[][] params) {
        try (Connection conn = dataSource.getConnection();
             PreparedStatement pstm = conn.prepareStatement(sql)) {
            for (Object[] p : params) {
                setParameters(pstm, p);
                pstm.addBatch();
            }
            return pstm.executeBatch();
        } catch (SQLException e) {
            throw new DatabaseException(e);
        }
    }

    protected List<T> executeQuery(String sql, RowMapper<T> mapper, Object... params) {
        List<T> result = new ArrayList<>();
        try (Connection connection = dataSource.getConnection();
             PreparedStatement pstm = connection.prepareStatement(sql)) {
            setParameters(pstm, params);
            try (ResultSet rs = pstm.executeQuery()) {
                while (rs.next()) {
                    result.add(mapper.mapRow(rs));
                }
            }
        } catch (SQLException e) {
            throw new DatabaseException(e);
        }
        return result;
    }

    @SuppressWarnings("unchecked")
    protected  <X> X executeSingleResultQuery(String sql, Class<X> targetClass, Object... params) {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement pstm = connection.prepareStatement(sql)) {
            setParameters(pstm, params);
            try (ResultSet rs = pstm.executeQuery()) {
                if (rs.next()) {
                    if (targetClass == Long.class) {
                        return (X)Long.valueOf(rs.getLong(1));
                    } else if (targetClass == String.class) {
                        return (X)rs.getString(1);
                    }
                }
            }
        } catch (SQLException e) {
            throw new DatabaseException(e);
        }
        return null;
    }

    protected Long generateId(String sql) {
        return executeSingleResultQuery(sql, Long.class);
    }

    protected Object getParam(Object param, Class<?> paramClass) {
        if (param != null) return param;

        if (paramClass == String.class) {
            return new NullParam(Types.VARCHAR);
        } else if (paramClass == Long.class) {
            return new NullParam(Types.BIGINT);
        } else if (paramClass == Integer.class) {
            return new NullParam(Types.INTEGER);
        } else if (paramClass == LocalDate.class) {
            return new NullParam(Types.DATE);
        } else if (paramClass == LocalDateTime.class) {
            return new NullParam(Types.TIMESTAMP);
        } else if (paramClass == LocalTime.class) {
            return new NullParam(Types.TIME);
        } else if (paramClass == BigDecimal.class) {
            return new NullParam(Types.NUMERIC);
        } else if (paramClass == Boolean.class) {
            return new NullParam(Types.BOOLEAN);
        } else if (paramClass == byte[].class) {
            return new NullParam(Types.BLOB);
        }
        return new NullParam(Types.NULL);
    }

    protected void setParameters(PreparedStatement pstm, Object... params) throws SQLException {
        if (params == null || params.length == 0) return;
        int pos = 1;
        for (Object param : params) {
            if (param instanceof String) {
                pstm.setString(pos++, (String) param);
            } else if (param instanceof Integer) {
                pstm.setInt(pos++, (Integer) param);
            } else if (param instanceof Long) {
                pstm.setLong(pos++, (Long) param);
            } else if (param instanceof LocalDate) {
                pstm.setDate(pos++, java.sql.Date.valueOf((LocalDate) param));
            } else if (param instanceof LocalDateTime) {
                pstm.setTimestamp(pos++, java.sql.Timestamp.valueOf((LocalDateTime)param));
            } else if (param instanceof LocalTime) {
                pstm.setTime(pos++, java.sql.Time.valueOf((LocalTime) param));
            } else if (param instanceof Enum) {
                pstm.setString(pos++, ((Enum)param).name());
            } else if (param instanceof BigDecimal) {
                pstm.setBigDecimal(pos++, (BigDecimal) param);
            } else if (param instanceof Boolean) {
                pstm.setBoolean(pos++, (Boolean) param);
            } else if (param instanceof byte[]) {
                pstm.setBytes(pos++, (byte[]) param);
            } else if (param instanceof NullParam) {
                pstm.setNull(pos++, ((NullParam)param).getType());
            } else {
                pstm.setObject(pos++, param);
            }
        }
    }

    protected <X extends BaseModel> Long cascadeCreate(X model, BaseDao<X> baseDao) {
        if (model != null) {
            if (model.getId() == null) {
                baseDao.create(model);
                return model.getId();
            }
            return model.getId();
        }
        return null;
    }

    public abstract void create(T model);
    public abstract void update(T model);

    protected abstract String getSql(String key);
    protected abstract RowMapper<T> getMapper();
}
