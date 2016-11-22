package ru.bigcheese.jsalon.model;

import javax.persistence.*;

@Entity
@Table(name = "users", uniqueConstraints = { @UniqueConstraint(columnNames = "username") })
@NamedQueries({
        @NamedQuery(name = User.FIND_BY_USERNAME,
                query = "select u from User u where u.username = :username")
})
public class User extends BaseModel {

    public static final String FIND_BY_USERNAME = "User.findByUsername";

    private String username;
    private String surname;
    private String name;
    private String patronymic;
    private Group group;

    @Id
    @SequenceGenerator(name = "userSeq", sequenceName = "users_id_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "userSeq")
    public Long getId() {
        return super.getId();
    }

    @Column(nullable = false)
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPatronymic() {
        return patronymic;
    }

    public void setPatronymic(String patronymic) {
        this.patronymic = patronymic;
    }

    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    @JoinTable(name = "users_groups",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "group_id"))
    public Group getGroup() {
        return group;
    }

    public void setGroup(Group group) {
        this.group = group;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User)) return false;
        if (!super.equals(o)) return false;

        User user = (User) o;

        if (getUsername() != null ? !getUsername().equals(user.getUsername()) : user.getUsername() != null)
            return false;
        if (getSurname() != null ? !getSurname().equals(user.getSurname()) : user.getSurname() != null) return false;
        if (getName() != null ? !getName().equals(user.getName()) : user.getName() != null) return false;
        if (getPatronymic() != null ? !getPatronymic().equals(user.getPatronymic()) : user.getPatronymic() != null)
            return false;
        return getGroup() != null ? getGroup().equals(user.getGroup()) : user.getGroup() == null;

    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (getUsername() != null ? getUsername().hashCode() : 0);
        result = 31 * result + (getSurname() != null ? getSurname().hashCode() : 0);
        result = 31 * result + (getName() != null ? getName().hashCode() : 0);
        result = 31 * result + (getPatronymic() != null ? getPatronymic().hashCode() : 0);
        result = 31 * result + (getGroup() != null ? getGroup().hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "User{" +
                "username='" + username + '\'' +
                ", surname='" + surname + '\'' +
                ", name='" + name + '\'' +
                ", patronymic='" + patronymic + '\'' +
                ", group=" + group +
                '}';
    }
}
