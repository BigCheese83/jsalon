package ru.bigcheese.jsalon.dao;

import ru.bigcheese.jsalon.model.User;

import javax.ejb.Local;

@Local
public interface UserDao extends BaseDao<User> {
    User findByUsername(String username);
}
