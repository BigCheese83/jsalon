package ru.bigcheese.jsalon.model;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Objects;

@Entity
public class Person extends BaseModel {

    private String surname;
    private String name;
    private String patronymic;
    private LocalDate birthDate;
    private Passport passport;
    private Address regAddress;
    private Address liveAddress;
    private Contact contact;

    @Id
    @SequenceGenerator(name = "personSeq", sequenceName = "person_id_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "personSeq")
    public Long getId() {
        return super.getId();
    }

    @Column(nullable = false)
    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    @Column(nullable = false)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPatronymic() {
        return patronymic;
    }

    public void setPatronymic(String patronymic) {
        this.patronymic = patronymic;
    }

    @Column(name = "birth_date")
    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "passport_id")
    public Passport getPassport() {
        return passport;
    }

    public void setPassport(Passport passport) {
        this.passport = passport;
    }

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "reg_address_id")
    public Address getRegAddress() {
        return regAddress;
    }

    public void setRegAddress(Address regAddress) {
        this.regAddress = regAddress;
    }

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "live_address_id")
    public Address getLiveAddress() {
        return liveAddress;
    }

    public void setLiveAddress(Address liveAddress) {
        this.liveAddress = liveAddress;
    }

    @OneToOne(optional = false, cascade = CascadeType.ALL)
    @JoinColumn(name = "contact_id")
    public Contact getContact() {
        return contact;
    }

    public void setContact(Contact contact) {
        this.contact = contact;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Person)) return false;
        if (!super.equals(o)) return false;
        Person person = (Person) o;
        return Objects.equals(getSurname(), person.getSurname()) &&
                Objects.equals(getName(), person.getName()) &&
                Objects.equals(getPatronymic(), person.getPatronymic()) &&
                Objects.equals(getBirthDate(), person.getBirthDate()) &&
                Objects.equals(getContact(), person.getContact());
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), getSurname(), getName(), getPatronymic(), getBirthDate(), getContact());
    }

    @Override
    public String toString() {
        return "Person{" + super.toString() + ", " +
                "surname='" + surname + '\'' +
                ", name='" + name + '\'' +
                ", patronymic='" + patronymic + '\'' +
                ", birthDate=" + birthDate +
                (passport != null ? ", passport=" + passport.toString() : "") +
                (contact != null ? ", contact=" + contact.toString() : "") +
                (regAddress != null ? ", regAddress=" + regAddress.toString() : "") +
                '}';
    }

}
