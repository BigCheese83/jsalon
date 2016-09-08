package ru.bigcheese.jsalon.model;

import java.time.LocalDate;
import java.util.Objects;

public class Passport extends BaseModel {

    private String country;
    private String series;
    private String number;
    private String issuedBy;
    private LocalDate issueDate;
    private String subdivision;

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getSeries() {
        return series;
    }

    public void setSeries(String series) {
        this.series = series;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getIssuedBy() {
        return issuedBy;
    }

    public void setIssuedBy(String issuedBy) {
        this.issuedBy = issuedBy;
    }

    public LocalDate getIssueDate() {
        return issueDate;
    }

    public void setIssueDate(LocalDate issueDate) {
        this.issueDate = issueDate;
    }

    public String getSubdivision() {
        return subdivision;
    }

    public void setSubdivision(String subdivision) {
        this.subdivision = subdivision;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Passport)) return false;
        if (!super.equals(o)) return false;
        Passport passport = (Passport) o;
        return Objects.equals(country, passport.country) &&
                Objects.equals(series, passport.series) &&
                Objects.equals(number, passport.number) &&
                Objects.equals(issuedBy, passport.issuedBy) &&
                Objects.equals(issueDate, passport.issueDate) &&
                Objects.equals(subdivision, passport.subdivision);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), country, series, number, issuedBy, issueDate, subdivision);
    }

    @Override
    public String toString() {
        return "Passport{" + super.toString() + ", " +
                "country='" + country + '\'' +
                ", series='" + series + '\'' +
                ", number='" + number + '\'' +
                ", issuedBy='" + issuedBy + '\'' +
                ", issueDate=" + issueDate +
                ", subdivision='" + subdivision + '\'' +
                '}';
    }
}
