package ru.bigcheese.jsalon.model;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(uniqueConstraints = { @UniqueConstraint(columnNames = {"name"}) })
@NamedQueries({
        @NamedQuery(name = Post.EXISTS_BY_NAME,
                query = "select p.name from Post p where p.name = :name")
})
public class Post extends BaseModel {

    public static final String EXISTS_BY_NAME = "Post.existsByName";

    private String name;
    private String description;
    private Set<Service> services = new HashSet<>();

    @Id
    @SequenceGenerator(name = "postSeq", sequenceName = "post_id_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "postSeq")
    public Long getId() {
        return super.getId();
    }

    @Column(nullable = false)
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

    @ManyToMany
    @JoinTable(name = "post_service",
            joinColumns = @JoinColumn(name = "post_id", referencedColumnName="id"),
            inverseJoinColumns = @JoinColumn(name = "service_id", referencedColumnName="id"))
    public Set<Service> getServices() {
        return services;
    }

    public void setServices(Set<Service> services) {
        this.services = services;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Post)) return false;
        if (!super.equals(o)) return false;
        Post post = (Post) o;
        return Objects.equals(getName(), post.getName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), getName());
    }

    @Override
    public String toString() {
        return "Post{" + super.toString() + ", " +
                "name='" + name + '\'' +
                ", services=" + services +
                '}';
    }

    @PreRemove
    private void removeFromServices() {
        for (Service s : getServices()) {
            s.getPosts().remove(this);
        }
    }
}
