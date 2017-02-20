package ru.bigcheese.jsalon.core;

public final class StringUtils {

    public static boolean isBlank(String str) {
        String s = str == null ? "" : str;
        return s.trim().isEmpty();
    }

    public static boolean notBlank(String str) {
        return !isBlank(str);
    }

    public static String stripToNull(String str) {
        if (str == null) {
            return null;
        }
        String s = str.trim();
        return s.isEmpty() ? null : s;
    }
}
