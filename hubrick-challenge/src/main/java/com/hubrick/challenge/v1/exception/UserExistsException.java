package com.hubrick.challenge.v1.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Created by kobi on 11/03/16.
 */
@ResponseStatus(HttpStatus.CONFLICT)
public class UserExistsException extends RuntimeException {

    public UserExistsException(String email) {
        super(String.format("A User with the email '%s' already exists in the system.", email));
    }

}
