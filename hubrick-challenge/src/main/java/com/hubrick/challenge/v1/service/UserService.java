package com.hubrick.challenge.v1.service;

import com.hubrick.challenge.v1.exception.UserNotFoundException;
import com.hubrick.challenge.v1.model.User;

import java.util.List;

/**
 * Created by kobi on 07/03/16.
 */
public interface UserService {

    User createUser(User user);

    void deleteUser(Long userId);

    User getUserById(Long userId);

    User updateUser(User user);

    List<User> getAllUsers();
}
