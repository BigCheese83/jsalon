package ru.bigcheese.jsalon.ejb;

import ru.bigcheese.jsalon.core.exception.FacadeException;
import ru.bigcheese.jsalon.dao.DiscountDao;
import ru.bigcheese.jsalon.model.Discount;
import ru.bigcheese.jsalon.model.to.DiscountTO;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.inject.Inject;
import javax.validation.Validator;
import java.util.List;
import java.util.Objects;
import java.util.Set;

import static java.util.stream.Collectors.toList;
import static ru.bigcheese.jsalon.core.enums.FacadeExceptionKey.ENTITY_NOT_FOUND;
import static ru.bigcheese.jsalon.ejb.EJBUtils.validateEntity;

@Stateless
public class DiscountFacadeBean implements DiscountFacade {

    @Inject
    private DiscountDao discountDao;
    @Inject
    private Validator validator;

    @TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
    @Override
    public List<DiscountTO> findAll() {
        return discountDao.findAll().stream().map(DiscountTO::new).collect(toList());
    }

    @Override
    public Discount createDiscount(DiscountTO discount) throws FacadeException {
        Objects.requireNonNull(discount, "Discount must be not null.");
        Discount entity = new Discount(discount);
        validateEntity(validator, entity, "Скидка не создана.");
        discountDao.create(entity);
        return entity;
    }

    @Override
    public Discount updateDiscount(DiscountTO discount) throws FacadeException {
        Objects.requireNonNull(discount, "Discount must be not null.");
        Discount entity = discountDao.findById(discount.getId());
        if (entity == null) {
            throw new FacadeException(ENTITY_NOT_FOUND, "Discount \"%s\" not found.", discount.getName());
        }
        entity.update(discount);
        validateEntity(validator, entity, "Скидка не обновлена.");
        discountDao.update(entity);
        return entity;
    }

    @Override
    public void deleteDiscount(Long id) {
        Objects.requireNonNull(id, "Id must be not null.");
        discountDao.delete(id);
    }

    @Override
    public void deleteDiscounts(Set<Long> ids) {
        if (ids == null || ids.isEmpty()) {
            throw new IllegalArgumentException("Not ids to delete.");
        }
        discountDao.deleteDiscounts(ids);
    }
}
