package ru.bigcheese.jsalon.model.enums;

public enum ScheduleEntryStatus {
    NEW,
    CANCELLED,
    FINISHED;

    public static ScheduleEntryStatus value(String name) {
        if (name == null) return null;
        return Enum.valueOf(ScheduleEntryStatus.class, name);
    }
}
