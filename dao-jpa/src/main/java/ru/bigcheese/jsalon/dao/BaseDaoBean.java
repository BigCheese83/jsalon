package ru.bigcheese.jsalon.dao;

import ru.bigcheese.jsalon.dao.paging.Page;
import ru.bigcheese.jsalon.dao.paging.PageRequest;
import ru.bigcheese.jsalon.model.BaseModel;

import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

public abstract class BaseDaoBean<T extends BaseModel> implements BaseDao<T> {

    @PersistenceContext
    private EntityManager entityManager;

    protected EntityManager getEntityManager() {
        return entityManager;
    }

    @Override
    public void create(T model) {
        entityManager.persist(model);
    }

    @Override
    public void update(T model) {
        entityManager.merge(model);
    }

    @Override
    public void delete(Long id) {
        entityManager.remove(entityManager.getReference(getModelClass(), id));
    }

    @TransactionAttribute(TransactionAttributeType.SUPPORTS)
    @Override
    public List<T> findAll() {
        Class<T> clazz = getModelClass();
        return entityManager.createQuery("select e from " + clazz.getName() + " e", clazz)
                .getResultList();
    }

    @TransactionAttribute(TransactionAttributeType.SUPPORTS)
    @Override
    public Page<T> findAll(PageRequest pageRequest) {
        Class<T> clazz = getModelClass();
        List<T> find = entityManager.createQuery("select e from " + clazz.getName() + " e", clazz)
                .setFirstResult(pageRequest.getOffset())
                .setMaxResults(pageRequest.getPageSize())
                .getResultList();
        Long count = countAll();
        return new Page<>(find, count, pageRequest.getPageSize());
    }

    @TransactionAttribute(TransactionAttributeType.SUPPORTS)
    @Override
    public T findById(Long id) {
        return entityManager.find(getModelClass(), id);
    }

    @TransactionAttribute(TransactionAttributeType.SUPPORTS)
    @Override
    public Long countAll() {
        return entityManager.createQuery("select count(e) from " + getModelClass().getName() + " e", Long.class)
                .getResultList().get(0);
    }

    @TransactionAttribute(TransactionAttributeType.SUPPORTS)
    @Override
    public boolean exists(Long id) {
        return id != null &&
                !entityManager.createQuery("select e.id from " + getModelClass().getName() + " e where e.id = :id")
                        .setParameter("id", id)
                        .getResultList().isEmpty();
    }

    protected abstract Class<T> getModelClass();
}
