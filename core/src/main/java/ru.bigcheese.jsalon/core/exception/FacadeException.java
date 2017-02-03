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

    public static <T> T checkNotNull(T object, FacadeExceptionKey key, String message, Object... args)
            throws FacadeException {
        if (object == null) {
            throw new FacadeException(key, message, args);
        } else {
            return object;
        }
    }

    public static void checkArgument(boolean condition, FacadeExceptionKey key, String message, Object... args)
            throws FacadeException {
        if (!condition) {
            throw new FacadeException(key, message, args);
        }
    }
}
