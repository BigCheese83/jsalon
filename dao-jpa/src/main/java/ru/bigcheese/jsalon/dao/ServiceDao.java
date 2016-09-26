package ru.bigcheese.jsalon.dao;

import ru.bigcheese.jsalon.model.Service;

import javax.ejb.Local;

@Local
public interface ServiceDao extends BaseDao<Service> {
    boolean existsByName(String name);
}
