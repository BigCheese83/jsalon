package ru.bigcheese.jsalon.core.exception;

public class OptimisticLockException extends RuntimeException {

    public OptimisticLockException() {
        super();
    }

    public OptimisticLockException(String message) {
        super(message);
    }
}
