package com.hubrick.challenge.v1.exception;

import com.hubrick.challenge.v1.model.InputField;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Created by kobi on 07/03/16.
 */
@ResponseStatus(HttpStatus.BAD_REQUEST)
public class InvalidInputException extends RuntimeException {

    public InvalidInputException(InputField field) {
        super(String.format("Value for field '%s' is not valid!", field.getFieldName()));
    }

}
