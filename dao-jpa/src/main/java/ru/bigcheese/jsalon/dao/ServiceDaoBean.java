package ru.bigcheese.jsalon.dao;

import ru.bigcheese.jsalon.model.Service;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;

@Stateless
public class ServiceDaoBean extends BaseDaoBean<Service> implements ServiceDao {

    @TransactionAttribute(TransactionAttributeType.SUPPORTS)
    @Override
    public boolean existsByName(String name) {
        return !getEntityManager().createNamedQuery(Service.EXISTS_BY_NAME)
                .setParameter("name", name)
                .getResultList().isEmpty();
    }

    @Override
    protected Class<Service> getModelClass() {
        return Service.class;
    }
}
