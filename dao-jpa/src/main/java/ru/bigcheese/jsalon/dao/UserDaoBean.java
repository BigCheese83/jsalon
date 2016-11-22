package ru.bigcheese.jsalon.dao;

import ru.bigcheese.jsalon.model.User;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import java.util.List;

import static ru.bigcheese.jsalon.model.User.FIND_BY_USERNAME;

@Stateless
public class UserDaoBean extends BaseDaoBean<User> implements UserDao {

    @TransactionAttribute(TransactionAttributeType.SUPPORTS)
    @Override
    public User findByUsername(String username) {
        List<User> find = getEntityManager()
                .createNamedQuery(FIND_BY_USERNAME, User.class)
                .setParameter("username", username)
                .getResultList();
        return find.isEmpty() ? null : find.get(0);
    }

    @Override
    protected Class<User> getModelClass() {
        return User.class;
    }
}
