package com.wannistudio.messageconverter;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
//@WebMvcTest(UserController.class)
@AutoConfigureMockMvc
class UserControllerTest {
    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    @Test
    void createUser_JSON() throws Exception {
        User user = new User();
        user.setUsername("wanni");
        user.setPassword("1234");
        String userJson= objectMapper.writeValueAsString(user);
        mockMvc.perform(post("/users/create")
                    .contentType(MediaType.APPLICATION_JSON)
                    .accept(MediaType.APPLICATION_JSON)
                    .content(userJson)
        )
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.username", is(equalTo("wanni"))))
                .andExpect(jsonPath("$.password", is(equalTo("1234"))))
        ;
    }

    @Test
    void createUser_XML() throws Exception {
        User user = new User();
        user.setUsername("wanni");
        user.setPassword("1234");
        String userJson= objectMapper.writeValueAsString(user);
        System.out.println(userJson);
        mockMvc.perform(post("/users/create")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_XML)
                .content(userJson)
        )
                .andExpect(status().isOk())
                .andExpect(xpath("/User/username").string("wanni"))
                .andExpect(xpath("/User/password").string("1234"))
        ;
    }
}