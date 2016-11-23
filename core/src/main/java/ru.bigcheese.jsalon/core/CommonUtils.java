package ru.bigcheese.jsalon.core;

import com.google.common.base.Strings;

public final class CommonUtils {

    public static String parseException(Throwable e) {
        if (e == null) return "";
        StringBuilder sb = new StringBuilder(Strings.nullToEmpty(e.getMessage()));
        Throwable cause;
        Throwable root = e;
        while ((cause = root.getCause())!= null && (cause != root)) {
            String message = cause.getMessage();
            if (!Strings.isNullOrEmpty(message) && sb.indexOf(message) < 0) {
                if (sb.length() > 0) {
                    sb.append(sb.toString().endsWith(".") ? " " : ". ");
                }
                sb.append(message);
            }
            root = cause;
        }
        if (sb.length() == 0) {
            sb.append(e.getClass().getName());
        }
        return sb.toString();
    }
}
