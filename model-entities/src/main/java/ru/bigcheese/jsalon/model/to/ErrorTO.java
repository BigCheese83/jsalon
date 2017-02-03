package ru.bigcheese.jsalon.model.to;

import java.io.Serializable;

public class ErrorTO implements Serializable {

    private String message = "";
    private int status;

    public ErrorTO() {}

    public ErrorTO(String message, int status) {
        this.message = message;
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "ErrorTO{" +
                "message='" + message + '\'' +
                ", status=" + status +
                '}';
    }
}
