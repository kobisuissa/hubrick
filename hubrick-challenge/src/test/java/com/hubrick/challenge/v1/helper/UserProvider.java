package com.hubrick.challenge.v1.helper;

import com.hubrick.challenge.v1.model.User;
import org.springframework.stereotype.Component;

/**
 * Created by kobi on 11/03/16.
 */
@Component
public class UserProvider {

    public User provide(String firstName, String lastName, String email, String password) {
        User user = new User();
        user.setEmail(email);
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setPassword(password);
        return user;
    }

}
