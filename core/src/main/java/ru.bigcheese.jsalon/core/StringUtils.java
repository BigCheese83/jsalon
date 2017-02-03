package ru.bigcheese.jsalon.core;

public final class StringUtils {

    public static boolean isBlank(String str) {
        String s = str == null ? "" : str;
        return s.trim().isEmpty();
    }
}
