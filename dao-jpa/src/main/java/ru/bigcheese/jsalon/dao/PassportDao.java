package ru.bigcheese.jsalon.dao;

import ru.bigcheese.jsalon.model.Passport;

import javax.ejb.Local;

@Local
public interface PassportDao extends BaseDao<Passport> {
}
