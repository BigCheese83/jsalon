package ru.bigcheese.jsalon.dao;

import ru.bigcheese.jsalon.model.Service;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import java.util.List;
import java.util.Set;

@Stateless
public class ServiceDaoBean extends BaseDaoBean<Service> implements ServiceDao {

    @TransactionAttribute(TransactionAttributeType.SUPPORTS)
    @Override
    public boolean existsByName(String name) {
        return !getEntityManager().createNamedQuery(Service.EXISTS_BY_NAME)
                .setParameter("name", name)
                .getResultList().isEmpty();
    }

    @TransactionAttribute(TransactionAttributeType.SUPPORTS)
    @Override
    public List<Service> getServicesByIds(Set<Long> ids) {
        return getEntityManager().createNamedQuery(Service.GET_BY_IDS, Service.class)
                .setParameter("ids", ids)
                .getResultList();
    }

    @TransactionAttribute(TransactionAttributeType.SUPPORTS)
    @SuppressWarnings("unchecked")
    @Override
    public List<Service> getServicesByPostId(Long postId) {
        String sql = "select s.* from service s " +
                "join post_service ps on s.id = ps.service_id " +
                "where ps.post_id = ?1";
        return getEntityManager().createNativeQuery(sql, Service.class)
                .setParameter(1, postId)
                .getResultList();
    }

    @Override
    protected Class<Service> getModelClass() {
        return Service.class;
    }
}
