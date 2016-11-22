package ru.bigcheese.jsalon.model;

public class Group extends BaseModel {

    private String name;
    private String description;

    public String getName() {
        return name;
    }

    public void setName(String name) {
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
        if (!(o instanceof Group)) return false;
        if (!super.equals(o)) return false;

        Group group = (Group) o;

        return name != null ? name.equals(group.name) : group.name == null;

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
                "name='" + name + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
