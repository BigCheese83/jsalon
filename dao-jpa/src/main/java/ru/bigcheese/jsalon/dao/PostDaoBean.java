package ru.bigcheese.jsalon.dao;

import ru.bigcheese.jsalon.model.Post;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import java.util.List;
import java.util.Set;

@Stateless
public class PostDaoBean extends BaseDaoBean<Post> implements PostDao {

    @TransactionAttribute(TransactionAttributeType.SUPPORTS)
    @Override
    public boolean existByName(String name) {
        return !getEntityManager().createNamedQuery(Post.EXISTS_BY_NAME)
                .setParameter("name", name)
                .getResultList().isEmpty();
    }

    @TransactionAttribute(TransactionAttributeType.SUPPORTS)
    @Override
    public List<Post> getPostsByIds(Set<Long> ids) {
        return getEntityManager().createNamedQuery(Post.GET_BY_IDS, Post.class)
                .setParameter("ids", ids)
                .getResultList();
    }

    @TransactionAttribute(TransactionAttributeType.SUPPORTS)
    @SuppressWarnings("unchecked")
    @Override
    public List<Post> getPostsByServiceId(Long serviceId) {
        String sql = "select p.* from post p " +
                "join post_service ps on p.id = ps.post_id " +
                "where ps.service_id = ?1";
        return getEntityManager().createNativeQuery(sql, Post.class)
                .setParameter(1, serviceId)
                .getResultList();
    }

    @Override
    public void deletePosts(Set<Long> ids) {
        getEntityManager().createNamedQuery(Post.DELETE_BY_IDS)
                .setParameter("ids", ids)
                .executeUpdate();
        getEntityManager().flush();
    }

    @Override
    protected Class<Post> getModelClass() {
        return Post.class;
    }
}
