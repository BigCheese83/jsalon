package ru.bigcheese.jsalon.dao;

import com.google.common.collect.ImmutableMap;
import ru.bigcheese.jsalon.dao.exception.OptimisticLockException;
import ru.bigcheese.jsalon.dao.mapper.PassportMapper;
import ru.bigcheese.jsalon.dao.mapper.RowMapper;
import ru.bigcheese.jsalon.model.Passport;

import javax.ejb.Stateless;
import java.time.LocalDate;
import java.util.Map;

@Stateless
public class PassportDaoBean extends BaseDaoBean<Passport> implements PassportDao {

    private static final Map<String, String> queries = new ImmutableMap.Builder<String, String>()
            .put(INSERT_KEY,
                    "insert into passport (id, created, modified, version, series, num, issued_by, issue_date, subdivision, country) " +
                    "values (?, now(), now(), 0, ?, ?, ?, ?, ?, ?)")
            .put(UPDATE_KEY,
                    "update passport set modified = now(), version = version + 1, " +
                    "series = ?, num = ?, issued_by = ?, issue_date = ?, subdivision = ?, country = ? " +
                    "where id = ? and version = ?")
            .put(DELETE_KEY, "delete from passport where id = ?")
            .put(FIND_ALL_KEY, "select * from passport order by modified")
            .put(FIND_ALL_LIMIT_KEY, "select * from passport order by modified limit ? offset ?")
            .put(FIND_BY_ID_KEY, "select * from passport where id = ?")
            .put(COUNT_KEY, "select count(*) from passport")
            .put(EXISTS_BY_ID_KEY, "select id from passport where id = ?")
            .build();

    private static final RowMapper<Passport> mapper = new PassportMapper();

    @Override
    public void create(Passport model) {
        Long id = generateId("select nextval('passport_id_seq')");
        executeUpdate(getSql(INSERT_KEY), id,
                getParam(model.getSeries(), String.class),
                getParam(model.getNumber(), String.class),
                getParam(model.getIssuedBy(), String.class),
                getParam(model.getIssueDate(), LocalDate.class),
                getParam(model.getSubdivision(), String.class),
                getParam(model.getCountry(), String.class));
        model.setId(id);
    }

    @Override
    public void update(Passport model) {
        int upd = executeUpdate(getSql(UPDATE_KEY),
                getParam(model.getSeries(), String.class),
                getParam(model.getNumber(), String.class),
                getParam(model.getIssuedBy(), String.class),
                getParam(model.getIssueDate(), LocalDate.class),
                getParam(model.getSubdivision(), String.class),
                getParam(model.getCountry(), String.class),
                model.getId(), model.getVersion());
        if (upd == 0) {
            throw new OptimisticLockException("Update failed. " +
                    "Passport with id =" + model.getId() + "has already been updated in another transaction.");
        }
    }

    @Override
    protected RowMapper<Passport> getMapper() {
        return mapper;
    }

    @Override
    protected String getSql(String key) {
        return queries.get(key);
    }
}
