package ru.bigcheese.jsalon.model;

import ru.bigcheese.jsalon.model.to.PostTO;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import static ru.bigcheese.jsalon.core.StringUtils.stripToNull;

@Entity
@Table(uniqueConstraints = { @UniqueConstraint(columnNames = {"name"}) })
@NamedQueries({
        @NamedQuery(name = Post.EXISTS_BY_NAME,
                query = "select p.name from Post p where p.name = :name"),
        @NamedQuery(name = Post.GET_BY_IDS,
                query = "select p from Post p where p.id in :ids"),
        @NamedQuery(name = Post.DELETE_BY_IDS,
                query = "delete from Post p where p.id in :ids")
})
public class Post extends BaseModel {

    public static final String EXISTS_BY_NAME = "Post.existsByName";
    public static final String GET_BY_IDS = "Post.getByIds";
    public static final String DELETE_BY_IDS = "Post.deleteByIds";

    private String name;
    private String description;
    private Set<Service> services = new HashSet<>();

    public Post() {}

    public Post(PostTO postTO) {
        setId(postTO.getId());
        update(postTO);
    }

    @Id
    @SequenceGenerator(name = "postSeq", sequenceName = "post_id_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "postSeq")
    public Long getId() {
        return super.getId();
    }

    @Column(nullable = false)
    @NotNull(message = "Name must be set.")
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

    @ManyToMany(mappedBy = "posts")
    public Set<Service> getServices() {
        return services;
    }

    public void setServices(Set<Service> services) {
        this.services = services;
    }

    public void addService(Service service) {
        services.add(service);
        service.getPosts().add(this);
    }

    public void update(PostTO postTO) {
        name = stripToNull(postTO.getName());
        description = stripToNull(postTO.getDescription());
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
