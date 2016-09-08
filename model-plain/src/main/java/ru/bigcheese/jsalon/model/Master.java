package ru.bigcheese.jsalon.model;

import java.time.LocalDate;
import java.util.Objects;

public class Master extends Person {

    private Person person;
    private LocalDate hiringDate;
    private Post post;

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public LocalDate getHiringDate() {
        return hiringDate;
    }

    public void setHiringDate(LocalDate hiringDate) {
        this.hiringDate = hiringDate;
    }

    public Post getPost() {
        return post;
    }

    public void setPost(Post post) {
        this.post = post;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Master)) return false;
        if (!super.equals(o)) return false;
        Master master = (Master) o;
        return Objects.equals(person, master.person);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), person);
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
