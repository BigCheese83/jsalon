package ru.bigcheese.jsalon.model;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.PreUpdate;
import javax.persistence.Version;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;

@MappedSuperclass
public abstract class BaseModel implements Serializable {

    private Long id;
    private LocalDateTime created = LocalDateTime.now();
    private LocalDateTime modified = LocalDateTime.now();
    private int version;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Column(updatable = false)
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

    @Version
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
        return Objects.equals(getCreated(), baseModel.getCreated());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getCreated());
    }

    @Override
    public String toString() {
        return "id=" + id + ", created=" + created + ", modified=" + modified;
    }

    @PreUpdate
    protected void updateModifiedDate() {
        modified = LocalDateTime.now();
    }
}
