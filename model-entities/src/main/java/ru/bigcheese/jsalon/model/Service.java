package ru.bigcheese.jsalon.model;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(uniqueConstraints = { @UniqueConstraint(columnNames = {"category", "name"}) })
@NamedQueries({
        @NamedQuery(name = Service.EXISTS_BY_NAME,
                query = "select s.name from Service s where s.name = :name"),
        @NamedQuery(name = Service.GET_BY_IDS,
                query = "select s from Service s where s.id in :ids")
})
public class Service extends BaseModel {

    public static final String EXISTS_BY_NAME = "Service.existsByName";
    public static final String GET_BY_IDS = "Service.getByIds";

    private String category;
    private String name;
    private BigDecimal cost;
    private Integer duration;
    private String description;
    private Set<Post> posts = new HashSet<>();

    @Id
    @SequenceGenerator(name = "serviceSeq", sequenceName = "service_id_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "serviceSeq")
    public Long getId() {
        return super.getId();
    }

    @Column(nullable = false)
    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    @Column(nullable = false)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column(nullable = false)
    public BigDecimal getCost() {
        return cost;
    }

    public void setCost(BigDecimal cost) {
        this.cost = cost;
    }

    @Column(nullable = false)
    public Integer getDuration() {
        return duration;
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @ManyToMany(mappedBy = "services")
    public Set<Post> getPosts() {
        return posts;
    }

    public void setPosts(Set<Post> posts) {
        this.posts = posts;
    }

    public void addPost(Post post) {
        posts.add(post);
        if (!post.getServices().contains(this)) {
            post.getServices().add(this);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Service)) return false;
        if (!super.equals(o)) return false;
        Service service = (Service) o;
        return Objects.equals(getCategory(), service.getCategory()) &&
                Objects.equals(getName(), service.getName()) &&
                Objects.equals(getCost(), service.getCost()) &&
                Objects.equals(getDuration(), service.getDuration());
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), getCategory(), getName(), getCost(), getDuration());
    }

    @Override
    public String toString() {
        return "Service{" +
                "category='" + category + '\'' +
                ", name='" + name + '\'' +
                ", cost=" + cost +
                ", duration=" + duration +
                ", description='" + description + '\'' +
                ", posts=" + posts +
                '}';
    }

    @PreRemove
    private void removeFromPosts() {
        for (Post p : getPosts()) {
            p.getServices().remove(this);
        }
    }
}
