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
public class FirstNameValidator implements Validator<String> {

    private static final int MAX_LENGTH = 30;
    private static final int MIN_LENGTH = 3;

    @Override
    public void validate(String firstName) throws InvalidInputException {

        boolean valid = true;

        valid = !GenericValidator.isBlankOrNull(firstName);

        valid = valid && GenericValidator.maxLength(firstName, MAX_LENGTH);

        valid = valid && GenericValidator.minLength(firstName, MIN_LENGTH);

        valid = valid && StringUtils.isAlphaSpace(firstName);

        if (!valid) {
            throw new InvalidInputException(InputField.FIRST_NAME);
        }

    }
}
