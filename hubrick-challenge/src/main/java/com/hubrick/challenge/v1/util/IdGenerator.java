package com.hubrick.challenge.v1.util;

import org.springframework.stereotype.Component;

/**
 * Created by kobi on 11/03/16.
 *
 * A very simple Id Generator returning a globally unique Long id
 */
@Component
public class IdGenerator {

    public Long getId() {
        return System.currentTimeMillis();
    }

}
