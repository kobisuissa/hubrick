package com.hubrick.challenge.v1.util;

import com.hubrick.challenge.v1.exception.InvalidInputException;

/**
 * Created by kobi on 10/03/16.
 */
public interface Validator<T> {

    void validate(T object) throws InvalidInputException;

}
