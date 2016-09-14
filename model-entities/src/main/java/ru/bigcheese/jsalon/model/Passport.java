package ru.bigcheese.jsalon.model;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Objects;

@Entity
@Table(uniqueConstraints = { @UniqueConstraint(columnNames = {"series", "num"}) })
public class Passport extends BaseModel {

    private String country;
    private String series = "";
    private String number;
    private String issuedBy;
    private LocalDate issueDate;
    private String subdivision;

    @Id
    @SequenceGenerator(name = "passportSeq", sequenceName = "passport_id_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "passportSeq")
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

    @Column(nullable = false)
    public String getSeries() {
        return series;
    }

    public void setSeries(String series) {
        this.series = series;
    }

    @Column(name = "num", nullable = false)
    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    @Column(name = "issued_by", nullable = false)
    public String getIssuedBy() {
        return issuedBy;
    }

    public void setIssuedBy(String issuedBy) {
        this.issuedBy = issuedBy;
    }

    @Column(name = "issue_date", nullable = false)
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
        return Objects.equals(getCountry(), passport.getCountry()) &&
                Objects.equals(getSeries(), passport.getSeries()) &&
                Objects.equals(getNumber(), passport.getNumber()) &&
                Objects.equals(getIssuedBy(), passport.getIssuedBy()) &&
                Objects.equals(getIssueDate(), passport.getIssueDate()) &&
                Objects.equals(getSubdivision(), passport.getSubdivision());
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), getCountry(), getSeries(), getNumber(), getIssuedBy(), getIssueDate(), getSubdivision());
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
