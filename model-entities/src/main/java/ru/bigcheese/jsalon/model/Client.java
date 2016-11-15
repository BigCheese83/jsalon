package ru.bigcheese.jsalon.model;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Objects;

@Entity
public class Client extends BaseModel {

    private Person person;
    private Discount discount;
    private LocalDate registrationDate;
    private boolean inBlackList = false;

    @Id
    @SequenceGenerator(name = "clientSeq", sequenceName = "client_id_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "clientSeq")
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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "discount_id")
    public Discount getDiscount() {
        return discount;
    }

    public void setDiscount(Discount discount) {
        this.discount = discount;
    }

    @Column(name = "reg_date", nullable = false, updatable = false)
    public LocalDate getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(LocalDate registrationDate) {
        this.registrationDate = registrationDate;
    }

    @Column(name = "in_blacklist", nullable = false)
    public boolean isInBlackList() {
        return inBlackList;
    }

    public void setInBlackList(boolean inBlackList) {
        this.inBlackList = inBlackList;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Client)) return false;
        if (!super.equals(o)) return false;
        Client client = (Client) o;
        return Objects.equals(getPerson(), client.getPerson());
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), getPerson());
    }

    @Override
    public String toString() {
        return "Client{" + super.toString() + ", " +
                (person != null ? ", person=" + person.toString() : "") +
                "registrationDate=" + registrationDate +
                (discount != null ? ", discount=" + discount.toString() : "") +
                ", inBlackList=" + inBlackList +
                '}';
    }
}
