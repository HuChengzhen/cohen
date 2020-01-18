package com.huchengzhen.cohen.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class UserFollowControllerTests {
    @Autowired
    private MockMvc mockMvc;

    @Test
    @Transactional
    @WithUserDetails(value = "hcz", userDetailsServiceBeanName = "userService")
    public void followTests() throws Exception {
        /*
            {
                "follower": 9,
                "followed": 23
            }
         */

        mockMvc.perform(post("/api/follow")
                .contentType(MediaType.APPLICATION_JSON)
                .content("            {\n" +
                        "                \"follower\": 9,\n" +
                        "                \"followed\": 23\n" +
                        "            }"))
                .andExpect(status().isOk())
                .andExpect(content().string("1"));
    }

    @Test
    @Transactional
    @WithUserDetails(value = "hcz", userDetailsServiceBeanName = "userService")
    public void cancelTests() throws Exception {
        mockMvc.perform(post("/api/follow")
                .contentType(MediaType.APPLICATION_JSON)
                .content("            {\n" +
                        "                \"follower\": 9,\n" +
                        "                \"followed\": 23\n" +
                        "            }"))
                .andExpect(status().isOk())
                .andExpect(content().string("1"));

        mockMvc.perform(delete("/api/follow")
                .contentType(MediaType.APPLICATION_JSON)
                .content("            {\n" +
                        "                \"follower\": 9,\n" +
                        "                \"followed\": 23\n" +
                        "            }"))
                .andExpect(status().isOk())
                .andExpect(content().string("1"));
    }
}
