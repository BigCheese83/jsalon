package ru.bigcheese.jsalon.model;

import java.math.BigDecimal;
import java.util.Objects;
import java.util.Set;

public class Service extends BaseModel {

    private String category;
    private String name;
    private BigDecimal cost;
    private Integer duration;
    private String description;
    private Set<Post> posts;

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getCost() {
        return cost;
    }

    public void setCost(BigDecimal cost) {
        this.cost = cost;
    }

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

    public Set<Post> getPosts() {
        return posts;
    }

    public void setPosts(Set<Post> posts) {
        this.posts = posts;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Service)) return false;
        if (!super.equals(o)) return false;
        Service service = (Service) o;
        return Objects.equals(category, service.category) &&
                Objects.equals(name, service.name) &&
                Objects.equals(cost, service.cost) &&
                Objects.equals(duration, service.duration);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), category, name, cost, duration);
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
}
