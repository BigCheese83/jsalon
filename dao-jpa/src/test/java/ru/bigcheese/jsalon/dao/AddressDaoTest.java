package ru.bigcheese.jsalon.dao;

import org.junit.*;
import ru.bigcheese.jsalon.model.Address;

import javax.inject.Inject;

public class AddressDaoTest extends AbstractBaseDaoTest {

    @Inject
    private AddressDao addressDao;

    @Override
    protected AddressDao getDao() {
        return addressDao;
    }

    @Test
    public void testCreate() {
        Address a = new Address();
        a.setCountry("Россия");
        a.setCity("Москва");
        a.setStreet("Ленинский проспект");
        a.setHouse("141");
        a.setFlat("112");
        addressDao.create(a);
        Assert.assertEquals(countAll+1, addressDao.countAll().longValue());
    }

    @Test
    public void testUpdate() {
        Address a = addressDao.findById(1L);
        a.setDistrict("Москва");
        addressDao.update(a);
        Assert.assertEquals("Москва", addressDao.findById(1L).getDistrict());
    }

    @Test
    public void testDelete() {
        addressDao.delete(4L);
        Assert.assertEquals(countAll-1, addressDao.countAll().longValue());
    }
}
