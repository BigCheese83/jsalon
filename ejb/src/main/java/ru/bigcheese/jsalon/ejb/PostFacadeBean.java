package ru.bigcheese.jsalon.ejb;

import ru.bigcheese.jsalon.core.exception.FacadeException;
import ru.bigcheese.jsalon.dao.PostDao;
import ru.bigcheese.jsalon.dao.ServiceDao;
import ru.bigcheese.jsalon.model.Post;
import ru.bigcheese.jsalon.model.to.PostTO;

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
public class PostFacadeBean implements PostFacade {

    @Inject
    private PostDao postDao;
    @Inject
    private ServiceDao serviceDao;
    @Inject
    private Validator validator;

    @TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
    @Override
    public List<PostTO> findAll() {
        return postDao.findAll().stream().map(PostTO::new).collect(toList());
    }

    @TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
    @Override
    public List<PostTO> getPostsByServiceId(Long serviceId) {
        return postDao.getPostsByServiceId(serviceId).stream().map(PostTO::new).collect(toList());
    }

    @Override
    public Post createPost(PostTO post) throws FacadeException {
        Objects.requireNonNull(post, "Post must be not null.");
        Post entity = new Post(post);
        if (post.getServices() != null) {
            entity.setServices(new HashSet<>(serviceDao.getServicesByIds(post.getServices())));
        }
        validateEntity(validator, entity, "Должность не создана.");
        postDao.create(entity);
        return entity;
    }

    @Override
    public Post updatePost(PostTO post) throws FacadeException {
        Objects.requireNonNull(post, "Post must be not null.");
        Post entity = postDao.findById(post.getId());
        if (entity == null) {
            throw new FacadeException(ENTITY_NOT_FOUND, "Post \"%s\" not found.", post.getName());
        }
        entity.update(post);
        if (post.getServices() != null) {
            entity.setServices(new HashSet<>(serviceDao.getServicesByIds(post.getServices())));
        }
        validateEntity(validator, entity, "Должность не обновлена.");
        postDao.update(entity);
        return entity;
    }

    @Override
    public void deletePost(Long id) {
        Objects.requireNonNull(id, "Id must be not null.");
        postDao.delete(id);
    }

    @Override
    public void deletePosts(Set<Long> ids) {
        if (ids == null || ids.isEmpty()) {
            throw new IllegalArgumentException("Not ids to delete.");
        }
        postDao.deletePosts(ids);
    }
}
