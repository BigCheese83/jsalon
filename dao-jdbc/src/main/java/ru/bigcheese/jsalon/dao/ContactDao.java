package ru.bigcheese.jsalon.dao;

import ru.bigcheese.jsalon.model.Contact;

import javax.ejb.Local;

@Local
public interface ContactDao extends BaseDao<Contact> {
}
