package com.huchengzhen.cohen.controller;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.pagehelper.PageInfo;
import com.huchengzhen.cohen.pojo.ArticleDetail;
import com.huchengzhen.cohen.pojo.User;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
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
@WithUserDetails(value = "hcz", userDetailsServiceBeanName = "userService")
public class ArticleControllerTests {
    @Autowired
    private MockMvc mockMvc;


    @Test
    @Transactional
    public void findArticleDetailByIdTests() throws Exception {
        mockMvc
                .perform(post("/api/article")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\n" +
                                "\t\"title\": \"qwercv\",\n" +
                                "\t\"article\": \"asgrcvoiuzv\"\n" +
                                "}"))
                .andExpect(status().isCreated());

        MvcResult result = mockMvc
                .perform(get("/api/article/all")
                        .queryParam("page", String.valueOf(Integer.MAX_VALUE))
                        .queryParam("size", "10"))
                .andExpect(status().isOk())
                .andReturn();

        ObjectMapper objectMapper = new ObjectMapper();

        PageInfo<ArticleDetail> pageInfo = objectMapper.readValue(result.getResponse().getContentAsString(), new TypeReference<PageInfo<ArticleDetail>>() {
        });
        int lastId = pageInfo.getList().get(pageInfo.getList().size() - 1).getId();
        mockMvc
                .perform(get("/api/article/detail/{id}", lastId))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", Matchers.equalTo(lastId)))
                .andExpect(jsonPath("$.author.username", Matchers.equalTo("hcz")))
                .andExpect(jsonPath("$.comments", Matchers.hasSize(0)))
                .andExpect(jsonPath("$.title", Matchers.equalTo("qwercv")))
                .andExpect(jsonPath("$.article", Matchers.equalTo("asgrcvoiuzv")));
    }

    @Test
    @Transactional
    public void findArticleByIdTests() throws Exception {
        Integer userId = ((User) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getId();

        mockMvc
                .perform(post("/api/article")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\n" +
                                "\t\"title\": \"qwercv\",\n" +
                                "\t\"article\": \"asgrcvoiuzv\"\n" +
                                "}"))
                .andExpect(status().isCreated());

        MvcResult result = mockMvc
                .perform(get("/api/article/all")
                        .queryParam("page", String.valueOf(Integer.MAX_VALUE))
                        .queryParam("size", "10"))
                .andExpect(status().isOk())
                .andReturn();
        ObjectMapper objectMapper = new ObjectMapper();

        PageInfo<ArticleDetail> pageInfo = objectMapper.readValue(result.getResponse().getContentAsString(), new TypeReference<PageInfo<ArticleDetail>>() {
        });
        int lastId = pageInfo.getList().get(pageInfo.getList().size() - 1).getId();

        mockMvc
                .perform(get("/api/article/{id}", lastId))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", Matchers.equalTo(lastId)))
                .andExpect(jsonPath("$.authorId", Matchers.equalTo(userId)))
                .andExpect(jsonPath("$.title", Matchers.equalTo("qwercv")))
                .andExpect(jsonPath("$.article", Matchers.equalTo("asgrcvoiuzv")));
    }

    @Test
    @Transactional
    public void deleteArticleByIdTests() throws Exception {
        mockMvc
                .perform(post("/api/article")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\n" +
                                "\t\"title\": \"qwercv\",\n" +
                                "\t\"article\": \"asgrcvoiuzv\"\n" +
                                "}"))
                .andExpect(status().isCreated());

        MvcResult result = mockMvc
                .perform(get("/api/article/all")
                        .queryParam("page", String.valueOf(Integer.MAX_VALUE))
                        .queryParam("size", "10"))
                .andExpect(status().isOk())
                .andReturn();
        ObjectMapper objectMapper = new ObjectMapper();

        PageInfo<ArticleDetail> pageInfo = objectMapper.readValue(result.getResponse().getContentAsString(), new TypeReference<PageInfo<ArticleDetail>>() {
        });
        int lastId = pageInfo.getList().get(pageInfo.getList().size() - 1).getId();

        mockMvc
                .perform(delete("/api/article/{id}", lastId))
                .andExpect(status().isOk());

        mockMvc
                .perform(get("/api/article/{id}", lastId))
                .andExpect(status().isOk())
                .andExpect(content().string(""));
    }

    @Test
    @Transactional
    @WithUserDetails(value = "cohen", userDetailsServiceBeanName = "userService")
    public void deleteArticleByIdByCohenTests() throws Exception {
        mockMvc
                .perform(post("/api/article")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\n" +
                                "\t\"title\": \"qwercv\",\n" +
                                "\t\"article\": \"asgrcvoiuzv\"\n" +
                                "}"))
                .andExpect(status().isCreated());

        MvcResult result = mockMvc
                .perform(get("/api/article/all")
                        .queryParam("page", String.valueOf(Integer.MAX_VALUE))
                        .queryParam("size", "10"))
                .andExpect(status().isOk())
                .andReturn();
        ObjectMapper objectMapper = new ObjectMapper();

        PageInfo<ArticleDetail> pageInfo = objectMapper.readValue(result.getResponse().getContentAsString(), new TypeReference<PageInfo<ArticleDetail>>() {
        });
        int lastId = pageInfo.getList().get(pageInfo.getList().size() - 1).getId();

        mockMvc
                .perform(delete("/api/article/{id}", lastId))
                .andExpect(status().isOk());

        mockMvc
                .perform(get("/api/article/{id}", lastId))
                .andExpect(status().isOk())
                .andExpect(content().string(""));
    }
}
