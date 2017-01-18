package ru.bigcheese.jsalon.model.enums;

public enum GroupName {
    ADMIN ("Администратор"), USER ("Пользователь");

    private final String label;

    GroupName(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }

    public static GroupName value(String name) {
        if (name == null) return null;
        return Enum.valueOf(GroupName.class, name);
    }
}
