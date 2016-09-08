package ru.bigcheese.jsalon.model;

import java.time.LocalDate;
import java.util.Objects;

public class Client extends BaseModel {

    private Person person;
    private LocalDate registrationDate;
    private Discount discount;
    private boolean inBlackList;

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public LocalDate getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(LocalDate registrationDate) {
        this.registrationDate = registrationDate;
    }

    public Discount getDiscount() {
        return discount;
    }

    public void setDiscount(Discount discount) {
        this.discount = discount;
    }

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
        return Objects.equals(person, client.person);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), person);
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
