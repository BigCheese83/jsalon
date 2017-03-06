package ru.bigcheese.jsalon.model.to;

import ru.bigcheese.jsalon.model.Post;

import java.io.Serializable;
import java.util.Set;

public class PostTO implements Serializable {

    private Long id;
    private String name;
    private String description;
    private Set<Long> services;

    public PostTO() {}

    public PostTO(Post post) {
        this.id = post.getId();
        this.name = post.getName();
        this.description = post.getDescription();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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

    public Set<Long> getServices() {
        return services;
    }

    public void setServices(Set<Long> services) {
        this.services = services;
    }

    @Override
    public String toString() {
        return "PostTO{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", services=" + services +
                '}';
    }
}
