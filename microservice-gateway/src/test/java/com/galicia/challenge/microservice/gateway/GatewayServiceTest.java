package com.galicia.challenge.microservice.gateway;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@SpringBootTest
class GatewayServiceTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void testChatRoute() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/api/chat/someMessage"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.messageResponse").exists());
    }

    @Test
    void testWeatherRoute() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/api/weather"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.weather").exists());
    }
}