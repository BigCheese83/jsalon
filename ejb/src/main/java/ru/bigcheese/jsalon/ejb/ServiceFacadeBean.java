package ru.bigcheese.jsalon.ejb;

import ru.bigcheese.jsalon.core.exception.FacadeException;
import ru.bigcheese.jsalon.dao.PostDao;
import ru.bigcheese.jsalon.dao.ServiceDao;
import ru.bigcheese.jsalon.model.Service;
import ru.bigcheese.jsalon.model.to.ServiceTO;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.inject.Inject;
import javax.validation.Validator;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

import static java.util.stream.Collectors.toList;
import static ru.bigcheese.jsalon.core.enums.FacadeExceptionKey.ENTITY_NOT_FOUND;
import static ru.bigcheese.jsalon.ejb.EJBUtils.validateEntity;

@Stateless
public class ServiceFacadeBean implements ServiceFacade {

    @Inject
    private ServiceDao serviceDao;
    @Inject
    private PostDao postDao;
    @Inject
    private Validator validator;

    @TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
    @Override
    public List<ServiceTO> findAll() {
        return serviceDao.findAll().stream().map(ServiceTO::new).collect(toList());
    }

    @TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
    @Override
    public List<ServiceTO> getServicesByPostId(Long postId) {
        return serviceDao.getServicesByPostId(postId).stream().map(ServiceTO::new).collect(toList());
    }

    @Override
    public Service createService(ServiceTO service) throws FacadeException {
        Objects.requireNonNull(service, "Service must be not null.");
        Service entity = new Service(service);
        if (service.getPosts() != null) {
            entity.setPosts(new HashSet<>(postDao.getPostsByIds(service.getPosts())));
        }
        validateEntity(validator, entity, "Услуга не создана.");
        serviceDao.create(entity);
        return entity;
    }

    @Override
    public Service updateService(ServiceTO service) throws FacadeException {
        Objects.requireNonNull(service, "Service must be not null.");
        Service entity = serviceDao.findById(service.getId());
        if (entity == null) {
            throw new FacadeException(ENTITY_NOT_FOUND, "Service \"%s\" not found.", service.getName());
        }
        entity.update(service);
        if (service.getPosts() != null) {
            entity.setPosts(new HashSet<>(postDao.getPostsByIds(service.getPosts())));
        }
        validateEntity(validator, entity, "Услуга не обновлена.");
        serviceDao.update(entity);
        return entity;
    }

    @Override
    public void deleteService(Long id) {
        Objects.requireNonNull(id, "Id must be not null.");
        serviceDao.delete(id);
    }

    @Override
    public void deleteServices(Set<Long> ids) {
        if (ids == null || ids.isEmpty()) {
            throw new IllegalArgumentException("Not ids to delete.");
        }
        serviceDao.deleteServices(ids);
    }
}
