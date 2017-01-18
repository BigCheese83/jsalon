package ru.bigcheese.jsalon.model;

import ru.bigcheese.jsalon.model.enums.GroupName;

public class Group extends BaseModel {

    private GroupName name;
    private String description;

    public GroupName getName() {
        return name;
    }

    public void setName(GroupName name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        Group group = (Group) o;

        return name == group.name;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (name != null ? name.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Group{" +
                "name=" + name +
                ", description='" + description + '\'' +
                '}';
    }
}
