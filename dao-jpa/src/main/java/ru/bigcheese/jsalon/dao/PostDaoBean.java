package ru.bigcheese.jsalon.dao;

import ru.bigcheese.jsalon.model.Post;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
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
