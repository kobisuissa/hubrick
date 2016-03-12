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
public class LastNameValidator implements Validator<String> {

    private static final int MAX_LENGTH = 50;
    private static final int MIN_LENGTH = 3;

    @Override
    public void validate(String lastName) throws InvalidInputException {

        boolean valid = true;

        valid = !GenericValidator.isBlankOrNull(lastName);

        valid = valid && GenericValidator.maxLength(lastName, MAX_LENGTH);

        valid = valid && GenericValidator.minLength(lastName, MIN_LENGTH);

        valid = valid && StringUtils.isAlphaSpace(lastName);

        if (!valid) {
            throw new InvalidInputException(InputField.LAST_NAME);
        }

    }
}
