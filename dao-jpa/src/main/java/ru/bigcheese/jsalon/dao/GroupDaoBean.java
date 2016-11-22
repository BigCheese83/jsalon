package ru.bigcheese.jsalon.dao;

import ru.bigcheese.jsalon.model.Group;

import javax.ejb.Stateless;

@Stateless
public class GroupDaoBean extends BaseDaoBean<Group> implements GroupDao {

    @Override
    protected Class<Group> getModelClass() {
        return Group.class;
    }
}
