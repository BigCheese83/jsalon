package ru.bigcheese.jsalon.model;

import ru.bigcheese.jsalon.model.to.DiscountTO;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.Objects;

import static ru.bigcheese.jsalon.core.StringUtils.stripToNull;

@Entity
@Table(uniqueConstraints = { @UniqueConstraint(columnNames = {"name"}) })
@NamedQueries({
        @NamedQuery(name = Discount.EXISTS_BY_NAME,
                query = "select d.name from Discount d where d.name = :name"),
        @NamedQuery(name = Discount.DELETE_BY_IDS,
                query = "delete from Discount d where d.id in :ids")
})
public class Discount extends BaseModel {

    public static final String EXISTS_BY_NAME = "Discount.existsByName";
    public static final String DELETE_BY_IDS = "Discount.deleteByIds";

    private String name;
    private Integer value;
    private String description;

    public Discount() {}

    public Discount(DiscountTO discountTO) {
        setId(discountTO.getId());
        this.name = stripToNull(discountTO.getName());
        this.value = discountTO.getValue();
        this.description = stripToNull(discountTO.getDescription());
    }

    @Id
    @SequenceGenerator(name = "discountSeq", sequenceName = "discount_id_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "discountSeq")
    public Long getId() {
        return super.getId();
    }

    @Column(nullable = false)
    @NotNull(message = "Name must be set.")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column(nullable = false)
    @NotNull(message = "Value must be set.")
    @Min(value = 1, message = "Value must be greater than zero.")
    @Max(value = 100, message = "Value must be lower than or equal to 100.")
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

    public void update(DiscountTO discountTO) {
        name = stripToNull(discountTO.getName());
        value = discountTO.getValue();
        description = stripToNull(discountTO.getDescription());
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
