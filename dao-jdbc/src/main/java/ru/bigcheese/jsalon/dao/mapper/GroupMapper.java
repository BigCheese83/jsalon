package ru.bigcheese.jsalon.dao.mapper;

import ru.bigcheese.jsalon.model.Group;
import ru.bigcheese.jsalon.model.enums.GroupName;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;

public class GroupMapper extends AbstractRowMapper<Group> {

    public GroupMapper() {}

    public GroupMapper(Map<String, String> mapAlias) {
        super(mapAlias);
    }

    @Override
    public Group mapRow(ResultSet rs) throws SQLException {
        Group group = new Group();
        group.setId(rs.getLong(get("id")));
        group.setCreated(toDateTime(rs.getTimestamp(get("created"))));
        group.setModified(toDateTime(rs.getTimestamp(get("modified"))));
        group.setVersion(rs.getInt(get("version")));
        group.setName(GroupName.value(rs.getString(get("groupname"))));
        group.setDescription(rs.getString(get("description")));
        return group;
    }
}
