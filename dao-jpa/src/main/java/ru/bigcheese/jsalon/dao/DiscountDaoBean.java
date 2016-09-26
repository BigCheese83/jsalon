package ru.bigcheese.jsalon.dao;

import ru.bigcheese.jsalon.model.Discount;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;

@Stateless
public class DiscountDaoBean extends BaseDaoBean<Discount> implements DiscountDao {

    @TransactionAttribute(TransactionAttributeType.SUPPORTS)
    @Override
    public boolean existByName(String name) {
        return !getEntityManager().createNamedQuery(Discount.EXISTS_BY_NAME)
                .setParameter("name", name)
                .getResultList().isEmpty();
    }

    @Override
    protected Class<Discount> getModelClass() {
        return Discount.class;
    }
}
