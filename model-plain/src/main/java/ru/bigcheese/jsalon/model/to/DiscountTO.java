package ru.bigcheese.jsalon.model.to;

import ru.bigcheese.jsalon.model.Discount;

import java.io.Serializable;

public class DiscountTO implements Serializable {

    private Long id;
    private String name;
    private Integer value;
    private String description;

    public DiscountTO() {}

    public DiscountTO(Discount discount) {
        this.id = discount.getId();
        this.name = discount.getName();
        this.value = discount.getValue();
        this.description = discount.getDescription();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    @Override
    public String toString() {
        return "DiscountTO{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", value=" + value +
                ", description='" + description + '\'' +
                '}';
    }
}
