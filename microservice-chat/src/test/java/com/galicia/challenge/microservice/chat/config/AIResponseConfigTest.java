package com.galicia.challenge.microservice.chat.config;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import java.util.Map;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
@TestPropertySource(properties = {
        "responses.answers.weather=The weather is %s.",
        "responses.answers.time=The time is %s.",
        "responses.answers.default=I didn't understand your question."
})
class AIResponseConfigTest {

    @Autowired
    private AIResponseConfig aiResponseConfig;

    @Test
    void testResponsesLoadFromConfiguration() {
        Map<String, String> answers = aiResponseConfig.getAnswers();

        assertNotNull(answers);
        assertEquals("The weather is %s.", answers.get("weather"));
        assertEquals("The time is %s.", answers.get("time"));
        assertEquals("I didn't understand your question.", answers.get("default"));
    }
}