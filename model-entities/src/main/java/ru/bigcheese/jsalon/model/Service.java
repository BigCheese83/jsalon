package ru.bigcheese.jsalon.model;

import ru.bigcheese.jsalon.model.to.ServiceTO;

import javax.persistence.*;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import static ru.bigcheese.jsalon.core.StringUtils.stripToNull;

@Entity
@Table(uniqueConstraints = { @UniqueConstraint(columnNames = {"category", "name"}) })
@NamedQueries({
        @NamedQuery(name = Service.EXISTS_BY_NAME,
                query = "select s.name from Service s where s.name = :name"),
        @NamedQuery(name = Service.GET_BY_IDS,
                query = "select s from Service s where s.id in :ids"),
        @NamedQuery(name = Service.DELETE_BY_IDS,
                query = "delete from Service s where s.id in :ids")
})
public class Service extends BaseModel {

    public static final String EXISTS_BY_NAME = "Service.existsByName";
    public static final String GET_BY_IDS = "Service.getByIds";
    public static final String DELETE_BY_IDS = "Service.deleteByIds";

    private String category;
    private String name;
    private BigDecimal cost;
    private Integer duration;
    private String description;
    private Set<Post> posts = new HashSet<>();

    public Service() {}

    public Service(ServiceTO serviceTO) {
        setId(serviceTO.getId());
        update(serviceTO);
    }

    @Id
    @SequenceGenerator(name = "serviceSeq", sequenceName = "service_id_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "serviceSeq")
    public Long getId() {
        return super.getId();
    }

    @Column(nullable = false)
    @NotNull(message = "Category must be set.")
    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    @Column(nullable = false)
    @NotNull(message = "Name must be set.")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column(nullable = false)
    @NotNull(message = "Cost must be set.")
    @DecimalMin(value = "0.01", message = "Cost must be greater than zero.")
    public BigDecimal getCost() {
        return cost;
    }

    public void setCost(BigDecimal cost) {
        this.cost = cost;
    }

    @Column(nullable = false)
    @NotNull(message = "Duration must be set.")
    @Min(value = 1, message = "Duration must be greater than zero.")
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

    @ManyToMany
    @JoinTable(name = "post_service",
            joinColumns = @JoinColumn(name = "service_id", referencedColumnName="id"),
            inverseJoinColumns = @JoinColumn(name = "post_id", referencedColumnName="id"))
    public Set<Post> getPosts() {
        return posts;
    }

    public void setPosts(Set<Post> posts) {
        this.posts = posts;
    }

    public void addPost(Post post) {
        posts.add(post);
        post.getServices().add(this);
    }

    public void update(ServiceTO serviceTO) {
        category = stripToNull(serviceTO.getCategory());
        name = stripToNull(serviceTO.getName());
        cost = serviceTO.getCost();
        duration = serviceTO.getDuration();
        description = stripToNull(serviceTO.getDescription());
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
