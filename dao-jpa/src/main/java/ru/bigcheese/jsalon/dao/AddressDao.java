package ru.bigcheese.jsalon.dao;

import ru.bigcheese.jsalon.model.Address;

import javax.ejb.Local;

@Local
public interface AddressDao extends BaseDao<Address> {
}
