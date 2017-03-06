package ru.bigcheese.jsalon.model.to;

import ru.bigcheese.jsalon.model.Service;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Set;

public class ServiceTO implements Serializable {

    private Long id;
    private String category;
    private String name;
    private BigDecimal cost;
    private Integer duration;
    private String description;
    private Set<Long> posts;

    public ServiceTO() {}

    public ServiceTO(Service service) {
        this.id = service.getId();
        this.category = service.getCategory();
        this.name = service.getName();
        this.cost = service.getCost();
        this.duration = service.getDuration();
        this.description = service.getDescription();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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

    public Set<Long> getPosts() {
        return posts;
    }

    public void setPosts(Set<Long> posts) {
        this.posts = posts;
    }

    @Override
    public String toString() {
        return "ServiceTO{" +
                "id=" + id +
                ", category='" + category + '\'' +
                ", name='" + name + '\'' +
                ", cost=" + cost +
                ", duration=" + duration +
                ", description='" + description + '\'' +
                ", posts=" + posts +
                '}';
    }
}
