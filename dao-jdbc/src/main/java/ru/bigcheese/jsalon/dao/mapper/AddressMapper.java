package ru.bigcheese.jsalon.dao.mapper;

import ru.bigcheese.jsalon.model.Address;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;

public class AddressMapper extends AbstractRowMapper<Address> {

    public AddressMapper() {}

    public AddressMapper(Map<String, String> mapAlias) {
        super(mapAlias);
    }

    @Override
    public Address mapRow(ResultSet rs) throws SQLException {
        Address address = new Address();
        address.setId(rs.getLong(get("id")));
        address.setCreated(toDateTime(rs.getTimestamp(get("created"))));
        address.setModified(toDateTime(rs.getTimestamp(get("modified"))));
        address.setVersion(rs.getInt(get("version")));
        address.setCountry(rs.getString(get("country")));
        address.setDistrict(rs.getString(get("district")));
        address.setCity(rs.getString(get("city")));
        address.setStreet(rs.getString(get("street")));
        address.setHouse(rs.getString(get("house")));
        address.setSection(rs.getString(get("section")));
        address.setFlat(rs.getString(get("flat")));
        address.setZip(rs.getString(get("zip")));
        return address;
    }
}
