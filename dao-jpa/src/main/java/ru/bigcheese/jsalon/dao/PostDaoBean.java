package ru.bigcheese.jsalon.dao;

import ru.bigcheese.jsalon.model.Post;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;

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
    protected Class<Post> getModelClass() {
        return Post.class;
    }
}
