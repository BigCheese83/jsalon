package ru.bigcheese.jsalon.dao;

import ru.bigcheese.jsalon.model.Person;

import javax.ejb.Stateless;

@Stateless
public class PersonDaoBean extends BaseDaoBean<Person> implements PersonDao {

    @Override
    protected Class<Person> getModelClass() {
        return Person.class;
    }
}
