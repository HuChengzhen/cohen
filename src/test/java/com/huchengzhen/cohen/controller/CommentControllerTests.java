package com.huchengzhen.cohen.controller;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.pagehelper.PageInfo;
import com.huchengzhen.cohen.pojo.Comment;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.transaction.annotation.Transactional;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class CommentControllerTests {
    @Autowired
    private MockMvc mockMvc;

    @Test
    @Transactional
    @WithUserDetails(value = "hcz", userDetailsServiceBeanName = "userService")
    public void insertCommentTests() throws Exception {
        /*
            {
                "comment": "comment",
                "articleId": 1
            }
        * */

        mockMvc
                .perform(post("/api/comment")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\n" +
                                "                \"comment\": \"comment\",\n" +
                                "                \"articleId\": 1\n" +
                                "            }  "))
                .andExpect(status().isCreated());

        MvcResult result = mockMvc
                .perform(get("/api/comment/all")
                        .queryParam("page", String.valueOf(Integer.MAX_VALUE))
                        .queryParam("size", "10"))
                .andExpect(status().isOk())
                .andReturn();

        ObjectMapper objectMapper = new ObjectMapper();
        PageInfo<Comment> pageInfo = objectMapper.readValue(result.getResponse().getContentAsString(), new TypeReference<PageInfo<Comment>>() {
        });
        Integer lastId = pageInfo.getList().get(pageInfo.getList().size() - 1).getId();
        mockMvc
                .perform(get("/api/comment/{id}", lastId))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", Matchers.equalTo(lastId)))
                .andExpect(jsonPath("$.comment", Matchers.equalTo("comment")))
                .andExpect(jsonPath("$.articleId", Matchers.equalTo(1)));

        mockMvc
                .perform(get("/api/comment")
                        .queryParam("articleId", "1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$..id").isArray())
                .andExpect(jsonPath("$..id", Matchers.hasItem(lastId)));
    }
}
