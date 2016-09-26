package ru.bigcheese.jsalon.dao;

import ru.bigcheese.jsalon.model.ScheduleEntry;

import javax.ejb.Local;

@Local
public interface ScheduleDao extends BaseDao<ScheduleEntry> {
}
