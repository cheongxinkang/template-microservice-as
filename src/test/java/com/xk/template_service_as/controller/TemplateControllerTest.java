package com.xk.template_service_as.controller;

import com.xk.template_service_as.dto.TemplateDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import tools.jackson.databind.ObjectMapper;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
public class TemplateControllerTest {

    private final WebApplicationContext context;
    private final ObjectMapper objectMapper;

    @Autowired
    TemplateControllerTest(WebApplicationContext context, ObjectMapper objectMapper) {
        this.context = context;
        this.objectMapper = objectMapper;
    }

    private TemplateDTO blankTemplate;
    private MockMvc mockMvc;

    @BeforeEach
    void setUp() throws Exception {
        mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
        blankTemplate = createBlankTemplate();
    }

    @Test
    void testCreateTemplate() throws Exception {
        mockMvc.perform(
            post("/templates/create")
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(blankTemplate)))
            .andExpect(status().isCreated());
    }

    private TemplateDTO createBlankTemplate() {
        return new TemplateDTO("Name");
    }

}
