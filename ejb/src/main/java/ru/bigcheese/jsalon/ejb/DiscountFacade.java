package ru.bigcheese.jsalon.ejb;

import ru.bigcheese.jsalon.core.exception.FacadeException;
import ru.bigcheese.jsalon.model.Discount;
import ru.bigcheese.jsalon.model.to.DiscountTO;

import javax.ejb.Local;
import java.util.List;
import java.util.Set;

@Local
public interface DiscountFacade {
    List<DiscountTO> findAll();
    Discount createDiscount(DiscountTO discount) throws FacadeException;
    Discount updateDiscount(DiscountTO discount) throws FacadeException;
    void deleteDiscount(Long id);
    void deleteDiscounts(Set<Long> ids);
}
