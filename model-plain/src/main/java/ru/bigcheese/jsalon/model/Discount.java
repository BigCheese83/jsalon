package ru.bigcheese.jsalon.model;

import ru.bigcheese.jsalon.model.to.DiscountTO;

import java.util.Objects;

import static ru.bigcheese.jsalon.core.StringUtils.stripToNull;

public class Discount extends BaseModel {

    private String name;
    private Integer value;
    private String description;

    public Discount() {}

    public Discount(DiscountTO discountTO) {
        setId(discountTO.getId());
        update(discountTO);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

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
        return Objects.equals(name, discount.name) &&
                Objects.equals(value, discount.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), name, value);
    }

    @Override
    public String toString() {
        return "Discount{" + super.toString() + ", " + "name='" + name + '\'' + ", value=" + value + '}';
    }
}
