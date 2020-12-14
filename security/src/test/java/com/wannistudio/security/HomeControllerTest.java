package com.wannistudio.security;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

@WebMvcTest(HomeController.class)
class HomeControllerTest {
    @Autowired
    MockMvc mockMvc;

    @Test
    @WithMockUser
    void hello_withUser() throws Exception {
        mockMvc.perform(get("/hello")
//                .accept(MediaType.TEXT_HTML)
        )
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(view().name("hello"))
        ;
    }

    @Test
    void hello_withoutUser() throws Exception {
        mockMvc.perform(get("/hello")
//        .accept(MediaType.TEXT_HTML)
        )
                .andDo(print())
                .andExpect(status().isUnauthorized())
                .andExpect(view().name("hello"))
        ;
    }

//    @Test
//    void my() throws Exception {
//        mockMvc.perform(get("/my"))
//                .andDo(print())
//                .andExpect(status().isOk())
//                .andExpect(view().name("my"))
//        ;
//    }
}