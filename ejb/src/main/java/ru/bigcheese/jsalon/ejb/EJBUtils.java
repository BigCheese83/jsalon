package ru.bigcheese.jsalon.ejb;

import ru.bigcheese.jsalon.core.exception.FacadeException;
import ru.bigcheese.jsalon.model.BaseModel;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.util.Set;
import java.util.stream.Collectors;

import static ru.bigcheese.jsalon.core.enums.FacadeExceptionKey.SET_INVALID_PARAMETER;

public final class EJBUtils {

    public static <T extends BaseModel> void validateEntity(Validator validator, T entity, String message)
            throws FacadeException {
        if (validator == null || entity == null) {
            return;
        }
        Set<ConstraintViolation<T>> errors = validator.validate(entity);
        if (!errors.isEmpty()) {
            throw new FacadeException(SET_INVALID_PARAMETER, "%s Найдено %d ошибок.\n%s",
                    message, errors.size(), getValidatorMessages(errors));
        }
    }

    public static <T> String getValidatorMessages(Set<ConstraintViolation<T>> violations) {
        if (violations == null || violations.isEmpty()) {
            return "";
        }
        return violations.stream()
                .map(e -> String.format("Property: %s, value: %s. %s",
                        e.getPropertyPath(), e.getInvalidValue(), e.getMessage()))
                .collect(Collectors.joining("\n"));
    }
}
