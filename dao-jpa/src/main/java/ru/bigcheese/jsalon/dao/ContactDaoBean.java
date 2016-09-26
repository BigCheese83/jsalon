package ru.bigcheese.jsalon.dao;

import ru.bigcheese.jsalon.model.Contact;

import javax.ejb.Stateless;

@Stateless
public class ContactDaoBean extends BaseDaoBean<Contact> implements ContactDao {

    @Override
    protected Class<Contact> getModelClass() {
        return Contact.class;
    }
}
