package com.hubrick.challenge.v1.service;

import com.hubrick.challenge.v1.exception.InvalidInputException;
import com.hubrick.challenge.v1.exception.UserExistsException;
import com.hubrick.challenge.v1.exception.UserNotFoundException;
import com.hubrick.challenge.v1.model.InputField;
import com.hubrick.challenge.v1.model.User;
import com.hubrick.challenge.v1.repository.UserRepository;
import com.hubrick.challenge.v1.util.IdGenerator;
import com.hubrick.challenge.v1.util.Validator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by kobi on 07/03/16.
 */
@Component
public class DefaultUserService implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    Validator userValidator;

    @Autowired
    private IdGenerator idGenerator;

    public User createUser(User user)  {
        user.setId(idGenerator.getId());
        userValidator.validate(user);
        if (userRepository.findByEmail(user.getEmail()) != null) {
            throw new UserExistsException(user.getEmail());
        }
        return userRepository.index(user);
    }

    public void deleteUser(Long userId) {
        User user = getUserById(userId);
        userRepository.delete(user);
    }

    public User getUserById(Long userId) {
        User user = userRepository.findOne(userId);
        if (user == null) {
            throw new UserNotFoundException(userId);
        } else {
            return user;
        }
    }

    @Override
    public User updateUser(User user) {
        if (userRepository.exists(user.getId())) {
            User userByEmail = userRepository.findByEmail(user.getEmail());
            if (userByEmail != null && (userByEmail.getId().compareTo(user.getId()) != 0)) {
                throw new UserExistsException(user.getEmail());
            }
            return userRepository.save(user);
        } else {
            throw new UserNotFoundException(user.getId());
        }
    }

    @Override
    public List<User> getAllUsers() {
        List<User> allUsers = new ArrayList<>();
        userRepository.findAll().forEach(allUsers::add);
        return allUsers;
    }
}
