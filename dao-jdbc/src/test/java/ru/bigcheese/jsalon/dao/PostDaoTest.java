package ru.bigcheese.jsalon.dao;

import org.junit.Assert;
import org.junit.Test;
import ru.bigcheese.jsalon.model.Post;

import javax.inject.Inject;

public class PostDaoTest extends AbstractBaseDaoTest {

    @Inject
    private PostDao postDao;

    @Override
    protected PostDao getDao() {
        return postDao;
    }

    @Test
    public void testCreate() {
        Post p = new Post();
        p.setName("Администратор");
        postDao.create(p);
        Assert.assertEquals(countAll + 1, postDao.countAll().longValue());
    }

    @Test
    public void testUpdate() {
        Post p = postDao.findById(1L);
        p.setDescription("Парикмахер, стилист");
        postDao.update(p);
        Assert.assertEquals("Парикмахер, стилист", postDao.findById(1L).getDescription());
    }

    @Test
    public void testDelete() {
        postDao.delete(2L);
        Assert.assertEquals(countAll - 1, postDao.countAll().longValue());
    }
}

