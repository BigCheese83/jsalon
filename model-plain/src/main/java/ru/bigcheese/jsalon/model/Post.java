package ru.bigcheese.jsalon.model;

import ru.bigcheese.jsalon.model.to.PostTO;

import java.util.Objects;
import java.util.Set;

import static ru.bigcheese.jsalon.core.StringUtils.stripToNull;

public class Post extends BaseModel {

    private String name;
    private String description;
    private Set<Service> services;

    public Post() {}

    public Post(PostTO postTO) {
        setId(postTO.getId());
        update(postTO);
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

    public Set<Service> getServices() {
        return services;
    }

    public void setServices(Set<Service> services) {
        this.services = services;
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
        return Objects.equals(name, post.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), name);
    }

    @Override
    public String toString() {
        return "Post{" + super.toString() + ", " +
                "name='" + name + '\'' +
                ", services=" + services +
                '}';
    }
}
