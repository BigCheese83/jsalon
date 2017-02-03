package ru.bigcheese.jsalon.dao;

import ru.bigcheese.jsalon.model.User;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.interceptor.ExcludeDefaultInterceptors;
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

    @ExcludeDefaultInterceptors
    @TransactionAttribute(TransactionAttributeType.SUPPORTS)
    @Override
    public boolean checkPassword(String username, String password) {
        List find = getEntityManager().createNativeQuery(
                "select password = md5(?1) from users where username = ?2")
                .setParameter(1, password)
                .setParameter(2, username)
                .getResultList();
        return !find.isEmpty() && (boolean)find.get(0);
    }

    @ExcludeDefaultInterceptors
    @Override
    public void setPassword(String username, String newPassword) {
        getEntityManager().createNativeQuery(
                "update users set password = md5(?1) where username = ?2")
                .setParameter(1, newPassword)
                .setParameter(2, username)
                .executeUpdate();
    }

    @Override
    protected Class<User> getModelClass() {
        return User.class;
    }
}
