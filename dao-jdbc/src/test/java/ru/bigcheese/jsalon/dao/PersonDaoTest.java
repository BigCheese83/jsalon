package ru.bigcheese.jsalon.dao;

import org.junit.Assert;
import org.junit.Test;
import ru.bigcheese.jsalon.model.Address;
import ru.bigcheese.jsalon.model.Contact;
import ru.bigcheese.jsalon.model.Passport;
import ru.bigcheese.jsalon.model.Person;

import javax.inject.Inject;
import java.time.LocalDate;

public class PersonDaoTest extends AbstractBaseDaoTest {

    @Inject
    private PersonDao personDao;

    @Override
    protected PersonDao getDao() {
        return personDao;
    }

    @Test
    public void testCreate() {
        Person p = new Person();
        p.setSurname("Ботов");
        p.setName("Дмитрий");
        p.setPatronymic("Иванович");
        p.setBirthDate(LocalDate.of(1986, 5, 8));
        Passport pass = new Passport();
        pass.setSeries("1156");
        pass.setNumber("011459");
        pass.setCountry("Россия");
        pass.setIssueDate(LocalDate.of(2006, 7, 12));
        pass.setIssuedBy("ОВД г.Толльяти");
        p.setPassport(pass);
        Address a = new Address();
        a.setCountry("Россия");
        a.setCity("Москва");
        a.setStreet("16-й магистральный проезд");
        a.setHouse("121A");
        a.setFlat("213");
        p.setRegAddress(a);
        p.setLiveAddress(a);
        Contact c = new Contact();
        c.setPhone("+7(926)932-43-00");
        p.setContact(c);
        personDao.create(p);
        Assert.assertEquals(countAll + 1, personDao.countAll().longValue());
    }

    @Test
    public void testUpdate() {
        Person p = personDao.findById(2L);
        p.setLiveAddress(null);
        personDao.update(p);
        Assert.assertNull("Парикмахер, стилист", personDao.findById(2L).getLiveAddress());
    }

    @Test
    public void testDelete() {
        personDao.delete(3L);
        Assert.assertEquals(countAll - 1, personDao.countAll().longValue());
    }
}
