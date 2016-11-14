package ru.bigcheese.jsalon.dao.mapper;

import com.google.common.collect.ImmutableMap;
import ru.bigcheese.jsalon.model.BaseModel;

import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Map;

public abstract class AbstractRowMapper<T extends BaseModel> implements RowMapper<T> {

    private Map<String, String> mapAlias;

    protected AbstractRowMapper() {}

    protected AbstractRowMapper(Map<String, String> mapAlias) {
        this.mapAlias = ImmutableMap.copyOf(mapAlias);
    }

    protected String get(String key) {
        return mapAlias == null ? key : mapAlias.get(key);
    }

    protected LocalDateTime toDateTime(Timestamp timestamp) {
        return timestamp == null ? null : timestamp.toLocalDateTime();
    }

    protected LocalDate toDate(Date date) {
        return date == null ? null : date.toLocalDate();
    }

    protected LocalTime toTime(Time time) {
        return time == null ? null : time.toLocalTime();
    }
}
