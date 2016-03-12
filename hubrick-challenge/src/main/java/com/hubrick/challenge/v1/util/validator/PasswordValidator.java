package com.hubrick.challenge.v1.util.validator;

import com.hubrick.challenge.v1.exception.InvalidInputException;
import com.hubrick.challenge.v1.model.InputField;
import com.hubrick.challenge.v1.util.Validator;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.validator.GenericValidator;
import org.springframework.stereotype.Component;

/**
 * Created by kobi on 11/03/16.
 */
@Component
public class PasswordValidator implements Validator<String> {

    private static final int MAX_LENGTH = 12;
    private static final int MIN_LENGTH = 8;

    @Override
    public void validate(String password) throws InvalidInputException {

        boolean valid = true;

        valid = !GenericValidator.isBlankOrNull(password);

        valid = valid && GenericValidator.maxLength(password, MAX_LENGTH);

        valid = valid && GenericValidator.minLength(password, MIN_LENGTH);

        valid = valid && StringUtils.isAlphanumeric(password);

        if (!valid) {
            throw new InvalidInputException(InputField.PASSWORD);
        }

    }
}
