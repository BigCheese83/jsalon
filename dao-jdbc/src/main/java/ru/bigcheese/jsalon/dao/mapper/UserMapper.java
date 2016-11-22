package ru.bigcheese.jsalon.dao.mapper;

import ru.bigcheese.jsalon.model.User;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class UserMapper extends AbstractRowMapper<User> {

    private final GroupMapper groupMapper;

    public UserMapper() {
        Map<String, String> gmap = new HashMap<>();
        gmap.put("id", "g_id");
        gmap.put("created", "g_created");
        gmap.put("modified", "g_modified");
        gmap.put("version", "g_version");
        gmap.put("groupname", "groupname");
        gmap.put("description", "g_description");
        groupMapper = new GroupMapper(gmap);
    }

    public UserMapper(Map<String, String> userAlias, Map<String, String> groupAlias) {
        super(userAlias);
        groupMapper = new GroupMapper(groupAlias);
    }

    @Override
    public User mapRow(ResultSet rs) throws SQLException {
        User user = new User();
        user.setId(rs.getLong(get("id")));
        user.setCreated(toDateTime(rs.getTimestamp(get("created"))));
        user.setModified(toDateTime(rs.getTimestamp(get("modified"))));
        user.setVersion(rs.getInt(get("version")));
        user.setUsername(rs.getString(get("username")));
        user.setSurname(rs.getString(get("surname")));
        user.setName(rs.getString(get("name")));
        user.setPatronymic(rs.getString(get("patronymic")));
        long groupId = rs.getLong(groupMapper.get("id"));
        if (groupId > 0) {
            user.setGroup(groupMapper.mapRow(rs));
        }
        return user;
    }
}
