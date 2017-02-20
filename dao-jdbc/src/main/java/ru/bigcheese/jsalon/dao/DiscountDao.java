package ru.bigcheese.jsalon.dao;

import ru.bigcheese.jsalon.model.Discount;

import javax.ejb.Local;
import java.util.Set;

@Local
public interface DiscountDao extends BaseDao<Discount> {
    boolean existByName(String name);
    void deleteDiscounts(Set<Long> ids);
}
