package com.hubrick.challenge.v1.controller;

import com.hubrick.challenge.v1.model.User;
import com.hubrick.challenge.v1.service.UserService;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.List;

/**
 * Created by kobi on 06/03/16.
 */
@RestController
@RequestMapping(value = "/users/v1", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
@Api(value = "Users", description = "The Users API provides access to the User Management system.")
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/{userId}", method = RequestMethod.GET)
    @ApiOperation(value = "Retrieve User", notes = "Returns the user details for the user with the given id.", response = User.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successful retrieval of user detail", response = User.class),
            @ApiResponse(code = 404, message = "User with given id does not exist"),
            @ApiResponse(code = 500, message = "Internal server error")}
    )
    User getUser(@ApiParam(name = "userId", value = "The user's auto-generated id.", required = true) @PathVariable Long userId) {
        return this.userService.getUserById(userId);
    }

    @RequestMapping(method = RequestMethod.GET)
    List<User> getUsers() {
        return this.userService.getAllUsers();
    }

    @RequestMapping(method = RequestMethod.POST)
    @ApiOperation(value = "Create User", notes = "Create a new User based on the user details provided.  The auto-generated userId is returned in the response headers.", response = User.class)
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful created new user."),
            @ApiResponse(code = 400, message = "Bad request, invalid input parameter."),
            @ApiResponse(code = 500, message = "Internal server error")}
    )
    ResponseEntity<?> addUser(@ApiParam(name = "user", value = "The user to be created in the user system.") @RequestBody User user) {
        Long userId = userService.createUser(user).getId();

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setLocation(ServletUriComponentsBuilder
                .fromCurrentRequest().path("/{id}")
                .buildAndExpand(userId).toUri());
        return new ResponseEntity<>(null, httpHeaders, HttpStatus.CREATED);
    }

    @RequestMapping(value = "/{userId}", method = RequestMethod.DELETE)
    @ApiOperation(value = "Delete User", notes = "Deletes the user associated with the provider userId", response = User.class)
    @ApiResponses(value = {
            @ApiResponse(code = 202, message = "Delete request successfully accepted by the system."),
            @ApiResponse(code = 500, message = "Internal server error")}
    )
    ResponseEntity<?> deleteUser(@ApiParam(name = "userId", value = "The user to be removed from the user system.") @PathVariable Long userId) {
        userService.deleteUser(userId);
        return new ResponseEntity<>(null, new HttpHeaders(), HttpStatus.ACCEPTED);
    }

    @RequestMapping(method = RequestMethod.PUT)
    @ApiOperation(value = "Update User", notes = "Create a new User based on the user details provided.  The auto-generated userId is returned in the response headers.", response = User.class)
    @ApiResponses(value = {
            @ApiResponse(code = 202, message = "Update request successfully accepted by the system."),
            @ApiResponse(code = 400, message = "Bad request, invalid input parameter."),
            @ApiResponse(code = 500, message = "Internal server error")}
    )
    ResponseEntity<?> updateUser(@ApiParam(name = "user", value = "The user to be updated in the user system.") @RequestBody User user) {
        User updatedUser = userService.updateUser(user);
        return new ResponseEntity<>(updatedUser, new HttpHeaders(), HttpStatus.ACCEPTED);
    }

}
