package ru.bigcheese.jsalon.dao;

import ru.bigcheese.jsalon.model.Service;

import javax.ejb.Local;
import java.util.List;
import java.util.Set;

@Local
public interface ServiceDao extends BaseDao<Service> {
    boolean existsByName(String name);
    void deleteServices(Set<Long> ids);
    List<Service> getServicesByIds(Set<Long> ids);
    List<Service> getServicesByPostId(Long postId);
}
