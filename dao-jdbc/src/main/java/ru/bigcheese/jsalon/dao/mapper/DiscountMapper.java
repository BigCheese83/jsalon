package ru.bigcheese.jsalon.dao.mapper;

import ru.bigcheese.jsalon.model.Discount;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;

public class DiscountMapper extends AbstractRowMapper<Discount> {

    public DiscountMapper() {}

    public DiscountMapper(Map<String, String> mapAlias) {
        super(mapAlias);
    }

    @Override
    public Discount mapRow(ResultSet rs) throws SQLException {
        Discount discount = new Discount();
        discount.setId(rs.getLong(get("id")));
        discount.setCreated(toDateTime(rs.getTimestamp(get("created"))));
        discount.setModified(toDateTime(rs.getTimestamp(get("modified"))));
        discount.setVersion(rs.getInt(get("version")));
        discount.setName(rs.getString(get("name")));
        discount.setValue(rs.getInt(get("value")));
        discount.setDescription(rs.getString(get("description")));
        return discount;
    }
}
