package com.hubrick.challenge.v1.helper;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.hubrick.challenge.v1.model.User;
import org.springframework.stereotype.Component;

/**
 * Created by kobi on 11/03/16.
 */
@Component
public class JsonProvider {

    private static final ObjectMapper objectMapper = new ObjectMapper();

    public String provide(User user) throws JsonProcessingException {
        return objectMapper.writeValueAsString(user);
    }

}
