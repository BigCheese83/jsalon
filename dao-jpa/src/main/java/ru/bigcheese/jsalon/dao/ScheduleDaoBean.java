package ru.bigcheese.jsalon.dao;

import ru.bigcheese.jsalon.model.ScheduleEntry;

import javax.ejb.Stateless;

@Stateless
public class ScheduleDaoBean extends BaseDaoBean<ScheduleEntry> implements ScheduleDao {

    @Override
    protected Class<ScheduleEntry> getModelClass() {
        return ScheduleEntry.class;
    }
}
