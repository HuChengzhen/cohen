package com.huchengzhen.cohen.controller;

import com.huchengzhen.cohen.pojo.User;
import com.jayway.jsonpath.JsonPath;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.transaction.annotation.Transactional;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class UserControllerTests {
    @Autowired
    private UserController userController;

    @Autowired
    MockMvc mockMvc;


    @Test
    @Transactional
    @WithUserDetails(value = "hcz", userDetailsServiceBeanName = "userService")
    public void deleteUserTests() throws Exception {

        int id = 11;
        mockMvc
                .perform(delete("/api/user/{id}", id))
                .andExpect(status().is2xxSuccessful());


        mockMvc.perform(post("/api/user/register")
                .contentType(MediaType.APPLICATION_JSON)
                .content("    {\n" +
                        "        \"username\": \"fack\",\n" +
                        "        \"password\": \"password\",\n" +
                        "        \"email\": \"fake@gmail.com\"\n" +
                        "    }")).andExpect(status().is(HttpStatus.CREATED.value()));
        Integer registerId = null;

        MvcResult result = mockMvc
                .perform(get("/api/user/name")
                        .queryParam("username", "fack"))
                .andExpect(jsonPath("$.id", Matchers.greaterThan(0)))
                .andExpect(jsonPath("$.username", Matchers.equalTo("fack")))
                .andReturn();

        registerId = JsonPath.read(result.getResponse().getContentAsString(), "$.id");

        mockMvc
                .perform(delete("/api/user/{id}", registerId))
                .andExpect(status().is2xxSuccessful());

        mockMvc
                .perform(get("/api/user/{id}", registerId))
                .andExpect(status().is(HttpStatus.OK.value()))
                .andExpect(content().string(""));
    }

    @Test
    @Transactional
    @WithUserDetails(value = "hcz", userDetailsServiceBeanName = "userService")
    public void registerTests() throws Exception {
        mockMvc.perform(post("/api/user/register")
                .contentType(MediaType.APPLICATION_JSON)
                .content("    {\n" +
                        "        \"username\": \"fack\",\n" +
                        "        \"password\": \"password\",\n" +
                        "        \"email\": \"fake@gmail.com\"\n" +
                        "    }")).andExpect(status().is(HttpStatus.CREATED.value()));

        mockMvc
                .perform(get("/api/user/name")
                        .queryParam("username", "fack"))
                .andExpect(jsonPath("$.id", Matchers.greaterThan(0)))
                .andExpect(jsonPath("$.username", Matchers.equalTo("fack")));
    }

    @Test
    @Transactional
    public void registerWrongEmailTests() throws Exception {
        mockMvc.perform(post("/api/user/register")
                .contentType(MediaType.APPLICATION_JSON)
                .content("    {\n" +
                        "        \"username\": \"fack\",\n" +
                        "        \"password\": \"password\",\n" +
                        "        \"email\": \"fakegmail.com\"\n" +
                        "    }"))
                .andExpect(status().is(HttpStatus.BAD_REQUEST.value()))
                .andExpect(content().string("Email wrong format"));
    }

    @Test
    @Transactional
    @WithUserDetails(value = "hcz", userDetailsServiceBeanName = "userService")
    public void currentUserTests() throws Exception {
        mockMvc
                .perform(get("/api/user"))
                .andExpect(status().is2xxSuccessful())
                .andExpect(jsonPath("$.id", Matchers.greaterThan(0)));
    }

    @Test
    @Transactional
    @WithUserDetails(value = "hcz", userDetailsServiceBeanName = "userService")
    public void findUserByIdTests() throws Exception {
        Integer id = ((User) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getId();

        mockMvc
                .perform(get("/api/user/{id}", id))
                .andExpect(status().is2xxSuccessful())
                .andExpect(jsonPath("$.id", Matchers.equalTo(id)))
                .andExpect(jsonPath("$.username", Matchers.equalTo("hcz")));


        // register
        mockMvc.perform(post("/api/user/register")
                .contentType(MediaType.APPLICATION_JSON)
                .content("    {\n" +
                        "        \"username\": \"fack\",\n" +
                        "        \"password\": \"password\",\n" +
                        "        \"email\": \"fake@gmail.com\"\n" +
                        "    }")).andExpect(status().is(HttpStatus.CREATED.value()));
        Integer registerId = null;

        MvcResult result = mockMvc
                .perform(get("/api/user/name")
                        .queryParam("username", "fack"))
                .andExpect(jsonPath("$.id", Matchers.greaterThan(0)))
                .andExpect(jsonPath("$.username", Matchers.equalTo("fack")))
                .andReturn();

        registerId = JsonPath.read(result.getResponse().getContentAsString(), "$.id");

        mockMvc
                .perform(get("/api/user/{id}", registerId))
                .andExpect(status().is2xxSuccessful())
                .andExpect(jsonPath("$.id", Matchers.equalTo(registerId)))
                .andExpect(jsonPath("$.username", Matchers.equalTo("fack")));

    }
}
