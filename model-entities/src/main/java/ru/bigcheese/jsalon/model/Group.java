package ru.bigcheese.jsalon.model;

import javax.persistence.*;

@Entity
@Table(name = "groups", uniqueConstraints = { @UniqueConstraint(columnNames = "groupname") })
public class Group extends BaseModel {

    private String name;
    private String description;

    @Id
    @SequenceGenerator(name = "groupSeq", sequenceName = "groups_id_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "groupSeq")
    public Long getId() {
        return super.getId();
    }

    @Column(name = "groupname", nullable = false)
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

        return getName() != null ? getName().equals(group.getName()) : group.getName() == null;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (getName() != null ? getName().hashCode() : 0);
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
