package ru.bigcheese.jsalon.dao;

import ru.bigcheese.jsalon.model.Client;
import ru.bigcheese.jsalon.model.Person;

import javax.ejb.Stateless;
import javax.inject.Inject;

@Stateless
public class ClientDaoBean extends BaseDaoBean<Client> implements ClientDao {

    @Inject
    private PersonDao personDao;

    @Override
    public void create(Client model) {
        Person person = model.getPerson();
        if (person.getId() == null) {
            personDao.create(person);
        }
        super.create(model);
    }

    @Override
    protected Class<Client> getModelClass() {
        return Client.class;
    }
}
