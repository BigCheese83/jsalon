package ru.bigcheese.jsalon.dao;

import ru.bigcheese.jsalon.core.paging.Page;
import ru.bigcheese.jsalon.core.paging.PageRequest;
import ru.bigcheese.jsalon.model.BaseModel;

import java.util.List;

public interface BaseDao<T extends BaseModel> {
    void create(T model);
    void update(T model);
    void delete(Long id);
    T findById(Long id);
    List<T> findAll();
    Page<T> findAll(PageRequest pageRequest);
    Long countAll();
    boolean exists(Long id);
}
