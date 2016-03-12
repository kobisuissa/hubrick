package com.hubrick.challenge.v1.util.validator;

import com.hubrick.challenge.v1.exception.InvalidInputException;
import com.hubrick.challenge.v1.model.User;
import com.hubrick.challenge.v1.util.Validator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by kobi on 11/03/16.
 */
@Component
public class UserValidator implements Validator<User> {

    @Autowired
    private Validator emailValidator;

    @Autowired
    private Validator firstNameValidator;

    @Autowired
    private Validator lastNameValidator;

    @Autowired
    private Validator passwordValidator;

    @Override
    public void validate(User user) throws InvalidInputException {

        emailValidator.validate(user.getEmail());

        firstNameValidator.validate(user.getFirstName());

        lastNameValidator.validate(user.getLastName());

        passwordValidator.validate(user.getPassword());

    }
}
