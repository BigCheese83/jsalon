package ru.bigcheese.jsalon.dao;

import com.google.common.collect.ImmutableMap;
import ru.bigcheese.jsalon.dao.exception.OptimisticLockException;
import ru.bigcheese.jsalon.dao.mapper.AddressMapper;
import ru.bigcheese.jsalon.dao.mapper.RowMapper;
import ru.bigcheese.jsalon.model.Address;

import javax.ejb.Stateless;
import java.util.Map;

@Stateless
public class AddressDaoBean extends BaseDaoBean<Address> implements AddressDao {

    private static final Map<String, String> queries = new ImmutableMap.Builder<String, String>()
            .put(INSERT_KEY,
                    "insert into address (id, created, modified, version, country, district, city, street, house, section, flat, zip) " +
                    "values (?, now(), now(), 0, ?, ?, ?, ?, ?, ?, ?, ?)")
            .put(UPDATE_KEY,
                    "update address set modified = now(), version = version + 1, " +
                    "country = ?, district = ?, city = ?, street = ?, house = ?, section = ?, flat = ?, zip = ? " +
                    "where id = ? and version = ?")
            .put(DELETE_KEY, "delete from address where id = ?")
            .put(FIND_ALL_KEY, "select * from address order by modified")
            .put(FIND_ALL_LIMIT_KEY, "select * from address order by modified limit ? offset ?")
            .put(FIND_BY_ID_KEY, "select * from address where id = ?")
            .put(COUNT_KEY, "select count(*) from address")
            .put(EXISTS_BY_ID_KEY, "select id from address where id = ?")
            .build();

    private static final RowMapper<Address> mapper = new AddressMapper();

    @Override
    public void create(Address model) {
        Long id = generateId("select nextval('address_id_seq')");
        executeUpdate(getSql(INSERT_KEY), id,
                getParam(model.getCountry(), String.class),
                getParam(model.getDistrict(), String.class),
                getParam(model.getCity(), String.class),
                getParam(model.getStreet(), String.class),
                getParam(model.getHouse(), String.class),
                getParam(model.getSection(), String.class),
                getParam(model.getFlat(), String.class),
                getParam(model.getZip(), String.class));
        model.setId(id);
    }

    @Override
    public void update(Address model) {
        int upd = executeUpdate(getSql(UPDATE_KEY),
                getParam(model.getCountry(), String.class),
                getParam(model.getDistrict(), String.class),
                getParam(model.getCity(), String.class),
                getParam(model.getStreet(), String.class),
                getParam(model.getHouse(), String.class),
                getParam(model.getSection(), String.class),
                getParam(model.getFlat(), String.class),
                getParam(model.getZip(), String.class),
                model.getId(), model.getVersion());
        if (upd == 0) {
            throw new OptimisticLockException("Update failed. " +
                    "Address with id =" + model.getId() + "has already been updated in another transaction.");
        }
    }

    @Override
    protected String getSql(String key) {
        return queries.get(key);
    }

    @Override
    protected RowMapper<Address> getMapper() {
        return mapper;
    }
}
