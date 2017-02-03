package ru.bigcheese.jsalon.ejb;

import ru.bigcheese.jsalon.core.exception.FacadeException;
import ru.bigcheese.jsalon.model.User;
import ru.bigcheese.jsalon.model.to.ProfileTO;

import javax.ejb.Local;

@Local
public interface UserFacade {
    User updateProfile(ProfileTO profile) throws FacadeException;
}
