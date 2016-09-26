package ru.bigcheese.jsalon.dao;

import org.junit.Assert;
import org.junit.Test;
import ru.bigcheese.jsalon.model.Contact;

import javax.inject.Inject;

public class ContactDaoTest extends AbstractBaseDaoTest {

    @Inject
    private ContactDao contactDao;

    @Override
    protected ContactDao getDao() {
        return contactDao;
    }

    @Test
    public void testCreate() {
        Contact c = new Contact();
        c.setPhone("+7(903)777-00-33");
        c.setEmail("botov.dm@sberbank.ru");
        c.setVk("botan86");
        contactDao.create(c);
        Assert.assertEquals(countAll + 1, contactDao.countAll().longValue());
    }

    @Test
    public void testUpdate() {
        Contact c = contactDao.findById(1L);
        c.setIcq("100-500-200");
        contactDao.update(c);
        Assert.assertEquals("100-500-200", contactDao.findById(1L).getIcq());
    }

    @Test
    public void testDelete() {
        contactDao.delete(3L);
        Assert.assertEquals(countAll-1, contactDao.countAll().longValue());
    }
}
