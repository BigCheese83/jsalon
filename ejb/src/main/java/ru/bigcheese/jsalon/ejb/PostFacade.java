package ru.bigcheese.jsalon.ejb;

import ru.bigcheese.jsalon.core.exception.FacadeException;
import ru.bigcheese.jsalon.model.Post;
import ru.bigcheese.jsalon.model.to.PostTO;

import javax.ejb.Local;
import java.util.List;
import java.util.Set;

@Local
public interface PostFacade {
    List<PostTO> findAll();
    List<PostTO> getPostsByServiceId(Long serviceId);
    Post createPost(PostTO post) throws FacadeException;
    Post updatePost(PostTO post) throws FacadeException;
    void deletePost(Long id);
    void deletePosts(Set<Long> ids);
}
