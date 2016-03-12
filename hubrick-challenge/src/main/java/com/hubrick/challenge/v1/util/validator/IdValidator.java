package com.hubrick.challenge.v1.util.validator;

import com.hubrick.challenge.v1.exception.InvalidInputException;
import com.hubrick.challenge.v1.model.InputField;
import com.hubrick.challenge.v1.util.Validator;
import org.apache.commons.validator.GenericValidator;
import org.springframework.stereotype.Component;

/**
 * Created by kobi on 11/03/16.
 */
@Component
public class IdValidator implements Validator<String> {

    @Override
    public void validate(String id) throws InvalidInputException {

        if (!GenericValidator.isLong(id)) {
            throw new InvalidInputException(InputField.USER_ID);
        }

    }
}
