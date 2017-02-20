package ru.bigcheese.jsalon.core.exception;

import ru.bigcheese.jsalon.core.enums.FacadeExceptionKey;

public class FacadeException extends Exception {

    private FacadeExceptionKey key;

    public FacadeException() {
        super();
    }

    public FacadeException(FacadeExceptionKey key, String message, Object... args) {
        super(String.format(message, args));
        this.key = key;
    }

    public FacadeExceptionKey getKey() {
        return key;
    }
}
