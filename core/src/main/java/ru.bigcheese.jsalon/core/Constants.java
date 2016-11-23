package ru.bigcheese.jsalon.core;

import java.time.Year;

public final class Constants {

    public static final int CURRENT_YEAR;
    public static final String SOFTWARE_NAME = "АИС \"Салон Красоты\"";
    public static final String VERSION_NUMBER = "v.1.0";
    public static final String VERSION_DATE = "14.12.2016";
    public static final String COPYRIGHT_STRING;

    static {
        CURRENT_YEAR = Year.now().getValue();
        COPYRIGHT_STRING = "\u00a9" + " " + SOFTWARE_NAME + " " + CURRENT_YEAR + " г.";
    }
}
