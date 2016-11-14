package ru.bigcheese.jsalon.dao.mapper;

import ru.bigcheese.jsalon.model.Passport;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;

public class PassportMapper extends AbstractRowMapper<Passport> {

    public PassportMapper() {}

    public PassportMapper(Map<String, String> mapAlias) {
        super(mapAlias);
    }

    @Override
    public Passport mapRow(ResultSet rs) throws SQLException {
        Passport passport = new Passport();
        passport.setId(rs.getLong(get("id")));
        passport.setCreated(toDateTime(rs.getTimestamp(get("created"))));
        passport.setModified(toDateTime(rs.getTimestamp(get("modified"))));
        passport.setVersion(rs.getInt(get("version")));
        passport.setSeries(rs.getString(get("series")));
        passport.setNumber(rs.getString(get("num")));
        passport.setIssuedBy(rs.getString(get("issued_by")));
        passport.setIssueDate(toDate(rs.getDate(get("issue_date"))));
        passport.setSubdivision(rs.getString(get("subdivision")));
        passport.setCountry(rs.getString(get("country")));
        return passport;
    }
}
