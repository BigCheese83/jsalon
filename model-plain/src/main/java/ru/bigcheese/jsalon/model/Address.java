package ru.bigcheese.jsalon.model;

import java.util.Objects;

public class Address extends BaseModel {

    private String country;
    private String district;
    private String city;
    private String street;
    private String house;
    private String section;
    private String flat;
    private String zip;

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

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getHouse() {
        return house;
    }

    public void setHouse(String house) {
        this.house = house;
    }

    public String getFlat() {
        return flat;
    }

    public void setFlat(String flat) {
        this.flat = flat;
    }

    public String getSection() {
        return section;
    }

    public void setSection(String section) {
        this.section = section;
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
        return Objects.equals(country, address.country) &&
                Objects.equals(district, address.district) &&
                Objects.equals(city, address.city) &&
                Objects.equals(street, address.street) &&
                Objects.equals(house, address.house) &&
                Objects.equals(section, address.section) &&
                Objects.equals(flat, address.flat) &&
                Objects.equals(zip, address.zip);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), country, district, city, street, house, section, flat, zip);
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
