package ru.bigcheese.jsalon.dao;

import ru.bigcheese.jsalon.model.Post;

import javax.ejb.Local;
import java.util.List;
import java.util.Set;

@Local
public interface PostDao extends BaseDao<Post> {
    boolean existByName(String name);
    void deletePosts(Set<Long> ids);
    List<Post> getPostsByIds(Set<Long> ids);
    List<Post> getPostsByServiceId(Long serviceId);
}
