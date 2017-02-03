package ru.bigcheese.jsalon.ejb;

import ru.bigcheese.jsalon.core.PasswordPolicy;
import ru.bigcheese.jsalon.core.exception.FacadeException;
import ru.bigcheese.jsalon.dao.UserDao;
import ru.bigcheese.jsalon.model.User;
import ru.bigcheese.jsalon.model.to.ProfileTO;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.Objects;

import static java.util.stream.Collectors.joining;
import static ru.bigcheese.jsalon.core.StringUtils.isBlank;
import static ru.bigcheese.jsalon.core.enums.FacadeExceptionKey.*;
import static ru.bigcheese.jsalon.core.exception.FacadeException.checkArgument;
import static ru.bigcheese.jsalon.core.exception.FacadeException.checkNotNull;

@Stateless
public class UserFacadeBean implements UserFacade {

    @Inject
    private UserDao userDao;

    @Override
    public User updateProfile(ProfileTO profile) throws FacadeException {
        Objects.requireNonNull(profile, "Profile must be not null.");
        User user = userDao.findByUsername(profile.getUsername());
        checkNotNull(user, ENTITY_NOT_FOUND, "User with username %s not found", profile.getUsername());
        checkArgument(userDao.checkPassword(profile.getUsername(), profile.getPassword()),
                INVALID_CREDENTIALS, "Invalid password");
        if (!isBlank(profile.getNewPassword())) {
            checkArgument(PasswordPolicy.DEFAULT.check(profile.getNewPassword()),
                    SET_INVALID_PARAMETER,
                    "New password invalid. Password must be:\n%s",
                    PasswordPolicy.DEFAULT.getPolicy().stream().collect(joining("\n")));
            userDao.setPassword(profile.getUsername(), profile.getNewPassword());
        }
        user.setSurname(profile.getSurname());
        user.setName(profile.getName());
        user.setPatronymic(profile.getPatronymic());
        userDao.update(user);
        return user;
    }
}
