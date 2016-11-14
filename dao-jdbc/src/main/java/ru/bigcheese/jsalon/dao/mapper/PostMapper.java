package ru.bigcheese.jsalon.dao.mapper;

import ru.bigcheese.jsalon.model.Post;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;

public class PostMapper extends AbstractRowMapper<Post> {

    public PostMapper() {}

    public PostMapper(Map<String, String> mapAlias) {
        super(mapAlias);
    }

    @Override
    public Post mapRow(ResultSet rs) throws SQLException {
        Post post = new Post();
        post.setId(rs.getLong(get("id")));
        post.setCreated(toDateTime(rs.getTimestamp(get("created"))));
        post.setModified(toDateTime(rs.getTimestamp(get("modified"))));
        post.setVersion(rs.getInt(get("version")));
        post.setName(rs.getString(get("name")));
        post.setDescription(rs.getString(get("description")));
        return post;
    }
}
