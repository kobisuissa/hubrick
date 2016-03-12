package com.hubrick.challenge.v1.controller;

import com.hubrick.challenge.Application;
import com.hubrick.challenge.v1.helper.JsonProvider;
import com.hubrick.challenge.v1.helper.UserProvider;
import com.hubrick.challenge.v1.model.User;
import com.hubrick.challenge.v1.repository.UserRepository;
import com.hubrick.challenge.v1.service.UserService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;

import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;


/**
 * Created by kobi on 11/03/16.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebAppConfiguration
public class UserControllerTest {

    private MediaType contentType = new MediaType(MediaType.APPLICATION_JSON.getType(),
            MediaType.APPLICATION_JSON.getSubtype(),
            Charset.forName("utf8"));

    @Autowired
    private WebApplicationContext webApplicationContext;

    @Autowired
    UserService userService;

    @Autowired
    UserRepository userRepository;

    @Autowired
    UserProvider userProvider;

    @Autowired
    JsonProvider jsonProvider;

    MockMvc mockMvc;

    List<User> setupUsers;

    @Before
    public void setup() throws Exception {
        mockMvc = webAppContextSetup(webApplicationContext).build();

        userRepository.deleteAll();

        setupUsers = new ArrayList<>();

        setupUsers.add(userService.createUser(userProvider.provide("Kobi", "Suissa", "kobisuissa@gmail.com", "Abcd1234")));
        setupUsers.add(userService.createUser(userProvider.provide("Jane", "Doe", "jane@doe.com", "Abcd1234")));
    }

    @Test
    public void testUserNotFound() throws Exception {
        mockMvc.perform(get("/users/v1/999")).andExpect(status().isNotFound());
    }

    @Test
    public void testBadRequestUserId() throws Exception {
        mockMvc.perform(get("/users/v1/9A9")).andExpect(status().isBadRequest());
    }

    @Test
    public void testBadRequestFirstNameTooShort() throws Exception {
        String userJson = jsonProvider.provide(userProvider.provide("To", "Jerry", "tom@jerry.com", "Abcd1234"));
        this.mockMvc.perform(post("/users/v1")
                .contentType(contentType)
                .content(userJson))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void testBadRequestFirstNameTooLong() throws Exception {
        String userJson = jsonProvider.provide(userProvider.provide("TomTomTomTomTomTomTomTomTomTomT", "Jerry", "tom@jerry.com", "Abcd1234"));
        this.mockMvc.perform(post("/users/v1")
                .contentType(contentType)
                .content(userJson))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void testBadRequestFirstNameIllegalChar() throws Exception {
        String userJson = jsonProvider.provide(userProvider.provide("Tom_", "Jerry", "tom@jerry.com", "Abcd1234"));
        this.mockMvc.perform(post("/users/v1")
                .contentType(contentType)
                .content(userJson))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void testBadRequestLastNameTooShort() throws Exception {
        String userJson = jsonProvider.provide(userProvider.provide("Tom", "Je", "tom@jerry.com", "Abcd1234"));
        this.mockMvc.perform(post("/users/v1")
                .contentType(contentType)
                .content(userJson))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void testBadRequestLastNameTooLong() throws Exception {
        String userJson = jsonProvider.provide(userProvider.provide("Tom", "JerryJerryJerryJerryJerryJerryJerryJerryJerryJerryJ", "tom@jerry.com", "Abcd1234"));
        this.mockMvc.perform(post("/users/v1")
                .contentType(contentType)
                .content(userJson))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void testBadRequestLastNameIllegalChar() throws Exception {
        String userJson = jsonProvider.provide(userProvider.provide("Tom", "Jerry;", "tom@jerry.com", "Abcd1234"));
        this.mockMvc.perform(post("/users/v1")
                .contentType(contentType)
                .content(userJson))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void readUser() throws Exception {
        User userToGet = setupUsers.get(0);
        mockMvc.perform(get("/users/v1/" + userToGet.getId()))
                .andExpect(status().isOk())
                .andExpect(content().contentType(contentType))
                .andExpect(jsonPath("$.id", is(userToGet.getId())))
                .andExpect(jsonPath("$.firstName", is(userToGet.getFirstName())))
                .andExpect(jsonPath("$.lastName", is(userToGet.getLastName())))
                .andExpect(jsonPath("$.password", is(userToGet.getPassword())))
                .andExpect(jsonPath("$.email", is(userToGet.getEmail())));
    }

    @Test
    public void testCreateUser() throws Exception {
        String userJson = jsonProvider.provide(userProvider.provide("Tom", "Jerry", "tom@jerry.com", "Abcd1234"));
        this.mockMvc.perform(post("/users/v1")
                .contentType(contentType)
                .content(userJson))
                .andExpect(status().isCreated());
    }

    @Test
    public void testCreateUserAlreadyExists() throws Exception {
        String userJson = jsonProvider.provide(userProvider.provide("Tom", "Jerry", "kobisuissa@gmail.com", "Abcd1234"));
        this.mockMvc.perform(post("/users/v1")
                .contentType(contentType)
                .content(userJson))
                .andExpect(status().isConflict());
    }

    @Test
    public void testUpdateUser() throws Exception {
        User kobi = setupUsers.get(0);
        kobi.setFirstName("Ibok");
        String userJson = jsonProvider.provide(kobi);
        this.mockMvc.perform(put("/users/v1")
                .contentType(contentType)
                .content(userJson))
                .andExpect(status().isAccepted())
                .andExpect(jsonPath("$.id", is(kobi.getId())))
                .andExpect(jsonPath("$.firstName", is(kobi.getFirstName())))
                .andExpect(jsonPath("$.lastName", is(kobi.getLastName())))
                .andExpect(jsonPath("$.password", is(kobi.getPassword())))
                .andExpect(jsonPath("$.email", is(kobi.getEmail())));
    }

    @Test
    public void testUpdateNonExistingUser() throws Exception {
        User kobi = setupUsers.get(0);
        kobi.setId(999L);
        kobi.setFirstName("Ibok");
        String userJson = jsonProvider.provide(kobi);
        this.mockMvc.perform(put("/users/v1")
                .contentType(contentType)
                .content(userJson))
                .andExpect(status().isNotFound());
    }

    @Test
    public void testUpdateUserEmailAlreadyExists() throws Exception {
        User kobi = setupUsers.get(0);
        kobi.setEmail("jane@doe.com");
        String userJson = jsonProvider.provide(kobi);
        this.mockMvc.perform(put("/users/v1")
                .contentType(contentType)
                .content(userJson))
                .andExpect(status().isConflict());
    }

    @Test
    public void testDeleteUser() throws Exception {
        this.mockMvc.perform(delete("/users/v1/" + setupUsers.get(1).getId()))
                .andExpect(status().isAccepted());
    }

    @Test
    public void testDeleteNonExistingUser() throws Exception {
        this.mockMvc.perform(delete("/users/v1/999"))
                .andExpect(status().isNotFound());
    }

}
