package ru.bigcheese.jsalon.model;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;

public abstract class BaseModel implements Serializable {

    private Long id;
    private LocalDateTime created;
    private LocalDateTime modified;
    private int version;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getCreated() {
        return created;
    }

    public void setCreated(LocalDateTime created) {
        this.created = created;
    }

    public LocalDateTime getModified() {
        return modified;
    }

    public void setModified(LocalDateTime modified) {
        this.modified = modified;
    }

    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof BaseModel)) return false;
        BaseModel baseModel = (BaseModel) o;
        return Objects.equals(created, baseModel.created);
    }

    @Override
    public int hashCode() {
        return Objects.hash(created);
    }

    @Override
    public String toString() {
        return "id=" + id + ", created=" + created + ", modified=" + modified;
    }
}
