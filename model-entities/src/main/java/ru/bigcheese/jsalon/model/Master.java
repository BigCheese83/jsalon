package ru.bigcheese.jsalon.model;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Objects;

@Entity
public class Master extends BaseModel {

    private Person person;
    private LocalDate hiringDate;
    private Post post;

    @Id
    @SequenceGenerator(name = "masterSeq", sequenceName = "master_id_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "masterSeq")
    public Long getId() {
        return super.getId();
    }

    @OneToOne(optional = false, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name = "person_id", nullable = false, updatable = false)
    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    @ManyToOne(optional = false)
    @JoinColumn(name = "post_id", nullable = false)
    public Post getPost() {
        return post;
    }

    public void setPost(Post post) {
        this.post = post;
    }

    @Column(name = "hiring_date")
    public LocalDate getHiringDate() {
        return hiringDate;
    }

    public void setHiringDate(LocalDate hiringDate) {
        this.hiringDate = hiringDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Master)) return false;
        if (!super.equals(o)) return false;
        Master master = (Master) o;
        return Objects.equals(getPerson(), master.getPerson());
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), getPerson());
    }

    @Override
    public String toString() {
        return "Master{" + super.toString() + ", " +
                (person != null ? ", person=" + person.toString() : "") +
                "hiringDate=" + hiringDate +
                (post != null ? ", post=" + post : "") +
                '}';
    }
}
