package ru.bigcheese.jsalon.dao;

import ru.bigcheese.jsalon.model.Person;

import javax.ejb.Local;

@Local
public interface PersonDao extends BaseDao<Person> {
}
