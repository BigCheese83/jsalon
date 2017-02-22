package ru.bigcheese.jsalon.dao.mapper;

import ru.bigcheese.jsalon.model.Service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;

public class ServiceMapper extends AbstractRowMapper<Service> {

    public ServiceMapper() {}

    public ServiceMapper(Map<String, String> mapAlias) {
        super(mapAlias);
    }

    @Override
    public Service mapRow(ResultSet rs) throws SQLException {
        Service service = new Service();
        service.setId(rs.getLong(get("id")));
        service.setCreated(toDateTime(rs.getTimestamp(get("created"))));
        service.setModified(toDateTime(rs.getTimestamp(get("modified"))));
        service.setVersion(rs.getInt(get("version")));
        service.setCategory(rs.getString(get("category")));
        service.setName(rs.getString(get("name")));
        service.setCost(rs.getBigDecimal(get("cost")));
        service.setDuration(rs.getInt(get("duration")));
        service.setDescription(rs.getString(get("description")));
        return service;
    }
}
