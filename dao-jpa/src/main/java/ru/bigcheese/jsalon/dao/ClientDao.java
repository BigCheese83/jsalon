package ru.bigcheese.jsalon.dao;

import ru.bigcheese.jsalon.model.Client;

import javax.ejb.Local;

@Local
public interface ClientDao extends BaseDao<Client> {
}
