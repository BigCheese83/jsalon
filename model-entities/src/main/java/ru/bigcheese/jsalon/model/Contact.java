package ru.bigcheese.jsalon.model;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(uniqueConstraints = { @UniqueConstraint(columnNames = {"phone"}) })
public class Contact extends BaseModel {

    private String phone;
    private String email;
    private String vk;
    private String skype;
    private String facebook;
    private String twitter;
    private String icq;

    @Id
    @SequenceGenerator(name = "contactSeq", sequenceName = "contact_id_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "contactSeq")
    public Long getId() {
        return super.getId();
    }

    @Column(nullable = false)
    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getVk() {
        return vk;
    }

    public void setVk(String vk) {
        this.vk = vk;
    }

    public String getSkype() {
        return skype;
    }

    public void setSkype(String skype) {
        this.skype = skype;
    }

    public String getFacebook() {
        return facebook;
    }

    public void setFacebook(String facebook) {
        this.facebook = facebook;
    }

    public String getTwitter() {
        return twitter;
    }

    public void setTwitter(String twitter) {
        this.twitter = twitter;
    }

    public String getIcq() {
        return icq;
    }

    public void setIcq(String icq) {
        this.icq = icq;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Contact)) return false;
        if (!super.equals(o)) return false;
        Contact contact = (Contact) o;
        return Objects.equals(getPhone(), contact.getPhone());
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), getPhone());
    }

    @Override
    public String toString() {
        return "Contact{" + super.toString() + ", " +
                "phone='" + phone + '\'' +
                ", email='" + email + '\'' +
                ", vk='" + vk + '\'' +
                ", skype='" + skype + '\'' +
                ", facebook='" + facebook + '\'' +
                ", twitter='" + twitter + '\'' +
                ", icq='" + icq + '\'' +
                '}';
    }
}
