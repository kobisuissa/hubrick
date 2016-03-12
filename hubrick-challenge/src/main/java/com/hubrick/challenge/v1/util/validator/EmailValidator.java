package com.hubrick.challenge.v1.util.validator;

import com.hubrick.challenge.v1.exception.InvalidInputException;
import com.hubrick.challenge.v1.model.InputField;
import com.hubrick.challenge.v1.util.Validator;
import org.springframework.stereotype.Component;

/**
 * Created by kobi on 10/03/16.
 */
@Component
public class EmailValidator implements Validator<String> {

    private static org.apache.commons.validator.routines.EmailValidator emailValidator = org.apache.commons.validator.routines.EmailValidator.getInstance(false);

    @Override
    public void validate(String email) throws InvalidInputException {
        if (!emailValidator.isValid(email)) {
            throw new InvalidInputException(InputField.EMAIL);
        }
    }
}
