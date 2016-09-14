package ru.bigcheese.jsalon.model;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(uniqueConstraints = { @UniqueConstraint(columnNames = {"name"}) })
@NamedQueries({
        @NamedQuery(name = Discount.EXISTS_BY_NAME,
                query = "select d.name from Discount d where d.name = :name")
})
public class Discount extends BaseModel {

    public static final String EXISTS_BY_NAME = "Discount.existsByName";

    private String name;
    private Integer value;
    private String description;

    @Id
    @SequenceGenerator(name = "discountSeq", sequenceName = "discount_id_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "discountSeq")
    public Long getId() {
        return super.getId();
    }

    @Column(nullable = false)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column(nullable = false)
    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Discount)) return false;
        if (!super.equals(o)) return false;
        Discount discount = (Discount) o;
        return Objects.equals(getName(), discount.getName()) &&
                Objects.equals(getValue(), discount.getValue());
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), getName(), getValue());
    }

    @Override
    public String toString() {
        return "Discount{" + super.toString() + ", " + "name='" + name + '\'' + ", value=" + value + '}';
    }
}
