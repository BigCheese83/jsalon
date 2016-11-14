package ru.bigcheese.jsalon.dao.exception;

import com.google.common.base.Joiner;
import com.google.common.collect.ImmutableList;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DatabaseException extends RuntimeException {

    private final List<String> messages = new ArrayList<>();

    public DatabaseException() {
        super();
    }

    public DatabaseException(SQLException sqlException) {
        if (sqlException != null) {
            for (Throwable t : sqlException) {
                if (t.getMessage() != null) {
                    messages.add(t.getMessage());
                }
            }
        }
    }

    public List<String> getMessages() {
        return ImmutableList.copyOf(messages);
    }

    @Override
    public String getMessage() {
        return Joiner.on(". ").join(messages);
    }
}
