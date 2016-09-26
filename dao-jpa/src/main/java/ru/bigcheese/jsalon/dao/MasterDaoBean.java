package ru.bigcheese.jsalon.dao;

import ru.bigcheese.jsalon.model.Master;

import javax.ejb.Stateless;

@Stateless
public class MasterDaoBean extends BaseDaoBean<Master> implements MasterDao {

    @Override
    protected Class<Master> getModelClass() {
        return Master.class;
    }
}
