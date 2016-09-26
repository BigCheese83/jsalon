package ru.bigcheese.jsalon.dao;

import ru.bigcheese.jsalon.model.Passport;

import javax.ejb.Stateless;

@Stateless
public class PassportDaoBean extends BaseDaoBean<Passport> implements PassportDao {

    @Override
    protected Class<Passport> getModelClass() {
        return Passport.class;
    }
}
