package ru.bigcheese.jsalon.dao;

import org.junit.Assert;
import org.junit.Test;
import ru.bigcheese.jsalon.model.Discount;

import javax.inject.Inject;

public class DiscountDaoTest extends AbstractBaseDaoTest {

    @Inject
    private DiscountDao discountDao;

    @Override
    protected DiscountDao getDao() {
        return discountDao;
    }

    @Test
    public void testCreate() {
        Discount d = new Discount();
        d.setName("Super-VIP");
        d.setValue(30);
        discountDao.create(d);
        Assert.assertEquals(countAll + 1, discountDao.countAll().longValue());
    }

    @Test
    public void testUpdate() {
        Discount d = discountDao.findById(1L);
        d.setValue(1);
        discountDao.update(d);
        Assert.assertEquals(1, discountDao.findById(1L).getValue().intValue());
    }

    @Test
    public void testDelete() {
        discountDao.delete(2L);
        Assert.assertEquals(countAll-1, discountDao.countAll().longValue());
    }
}
