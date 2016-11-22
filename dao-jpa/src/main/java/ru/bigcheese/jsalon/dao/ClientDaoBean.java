package ru.bigcheese.jsalon.dao;

import ru.bigcheese.jsalon.model.Client;

import javax.ejb.Stateless;

@Stateless
public class ClientDaoBean extends BaseDaoBean<Client> implements ClientDao {

    @Override
    protected Class<Client> getModelClass() {
        return Client.class;
    }
}
