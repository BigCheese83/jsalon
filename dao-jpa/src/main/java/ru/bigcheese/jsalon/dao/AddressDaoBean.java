package ru.bigcheese.jsalon.dao;

import ru.bigcheese.jsalon.model.Address;

import javax.ejb.Stateless;

@Stateless
public class AddressDaoBean extends BaseDaoBean<Address> implements AddressDao {

    @Override
    protected Class<Address> getModelClass() {
        return Address.class;
    }
}
