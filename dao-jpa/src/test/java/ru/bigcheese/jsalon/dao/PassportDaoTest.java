package ru.bigcheese.jsalon.dao;

import org.junit.Assert;
import org.junit.Test;
import ru.bigcheese.jsalon.model.Passport;

import javax.inject.Inject;
import java.time.LocalDate;

public class PassportDaoTest extends AbstractBaseDaoTest {

    @Inject
    private PassportDao passportDao;

    @Override
    protected PassportDao getDao() {
        return passportDao;
    }

    @Test
    public void testCreate() {
        Passport p = new Passport();
        p.setSeries("1234");
        p.setNumber("567890");
        p.setIssuedBy("ОВД г.Воронеж 12/4");
        p.setIssueDate(LocalDate.of(2002, 4, 12));
        p.setCountry("Россия");
        passportDao.create(p);
        Assert.assertEquals(countAll + 1, passportDao.countAll().longValue());
    }

    @Test
    public void testUpdate() {
        Passport p = passportDao.findById(1L);
        p.setSeries("0000");
        passportDao.update(p);
        Assert.assertEquals("0000", passportDao.findById(1L).getSeries());
    }

    @Test
    public void testDelete() {
        passportDao.delete(2L);
        Assert.assertEquals(countAll-1, passportDao.countAll().longValue());
    }
}
