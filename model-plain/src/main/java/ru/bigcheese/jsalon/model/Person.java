package ru.bigcheese.jsalon.model;

import java.time.LocalDate;
import java.util.Objects;

public class Person extends BaseModel {

    private String surname;
    private String name;
    private String patronymic;
    private LocalDate birthDate;
    private Passport passport;
    private Address regAddress;
    private Address liveAddress;
    private Contact contact;

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

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

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public Passport getPassport() {
        return passport;
    }

    public void setPassport(Passport passport) {
        this.passport = passport;
    }

    public Address getRegAddress() {
        return regAddress;
    }

    public void setRegAddress(Address regAddress) {
        this.regAddress = regAddress;
    }

    public Address getLiveAddress() {
        return liveAddress;
    }

    public void setLiveAddress(Address liveAddress) {
        this.liveAddress = liveAddress;
    }

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
        return Objects.equals(surname, person.surname) &&
                Objects.equals(name, person.name) &&
                Objects.equals(patronymic, person.patronymic) &&
                Objects.equals(birthDate, person.birthDate) &&
                Objects.equals(passport, person.passport) &&
                Objects.equals(regAddress, person.regAddress) &&
                Objects.equals(contact, person.contact);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), surname, name, patronymic, birthDate, passport, regAddress, contact);
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
