package com.xk.template_service_as.controller;

import com.xk.template_service_as.dto.TemplateDTO;
import com.xk.template_service_as.entity.FieldConverterTest;
import com.xk.template_service_as.entity.TemplateType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import tools.jackson.databind.ObjectMapper;

import java.time.Instant;
import java.time.Period;
import java.time.temporal.TemporalAmount;
import java.util.Date;
import java.util.UUID;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
public class TemplateControllerTest {

    private final WebApplicationContext context;
    private final ObjectMapper objectMapper;

    @Autowired
    TemplateControllerTest(WebApplicationContext context, ObjectMapper objectMapper) {
        this.context = context;
        this.objectMapper = objectMapper;
    }

    private MockMvc mockMvc;

    @BeforeEach
    void setUp() throws Exception {
        mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
    }

    @Test
    void testGetCreateForm() throws Exception {
        mockMvc.perform(get("/templates/create"))
            .andExpect(status().isOk())
            .andExpect(model().attributeExists("templateDTO"))
            .andExpect(view().name("home"));
    }

    @Test
    void testCreateFormSuccess() throws Exception {
        mockMvc.perform(
                post("/templates/create")
                    .param("templateName", "Test Template")
                    .param("type", String.valueOf(TemplateType.FORM))
                    .param("fields", String.valueOf(FieldConverterTest.createSampleFields()))
                    .param("createdAt", String.valueOf(Date.from(Instant.now())))
                    .param("modifiedAt", String.valueOf(Date.from(Instant.now()))))
            .andExpect(status().is3xxRedirection());
    }

}
