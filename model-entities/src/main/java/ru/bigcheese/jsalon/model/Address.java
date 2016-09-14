package ru.bigcheese.jsalon.model;

import javax.persistence.*;
import java.util.Objects;

@Entity
public class Address extends BaseModel {

    private String country;
    private String district;
    private String city;
    private String street;
    private String house;
    private String section;
    private String flat;
    private String zip;

    @Id
    @SequenceGenerator(name = "addressSeq", sequenceName = "address_id_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "addressSeq")
    public Long getId() {
        return super.getId();
    }

    @Column(nullable = false)
    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    @Column(nullable = false)
    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    @Column(nullable = false)
    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    @Column(nullable = false)
    public String getHouse() {
        return house;
    }

    public void setHouse(String house) {
        this.house = house;
    }

    public String getSection() {
        return section;
    }

    public void setSection(String section) {
        this.section = section;
    }

    @Column(nullable = false)
    public String getFlat() {
        return flat;
    }

    public void setFlat(String flat) {
        this.flat = flat;
    }

    public String getZip() {
        return zip;
    }

    public void setZip(String zip) {
        this.zip = zip;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Address)) return false;
        if (!super.equals(o)) return false;
        Address address = (Address) o;
        return Objects.equals(getCountry(), address.getCountry()) &&
                Objects.equals(getDistrict(), address.getDistrict()) &&
                Objects.equals(getCity(), address.getCity()) &&
                Objects.equals(getStreet(), address.getStreet()) &&
                Objects.equals(getHouse(), address.getHouse()) &&
                Objects.equals(getSection(), address.getSection()) &&
                Objects.equals(getFlat(), address.getFlat()) &&
                Objects.equals(getZip(), address.getZip());
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), getCountry(), getDistrict(), getCity(), getStreet(), getHouse(), getSection(), getFlat(), getZip());
    }

    @Override
    public String toString() {
        return "Address{" + super.toString() + ", " +
                "country='" + country + '\'' +
                ", district='" + district + '\'' +
                ", city='" + city + '\'' +
                ", street='" + street + '\'' +
                ", flat='" + flat + '\'' +
                ", house='" + house + '\'' +
                ", section='" + section + '\'' +
                ", zip='" + zip + '\'' +
                '}';
    }
}
