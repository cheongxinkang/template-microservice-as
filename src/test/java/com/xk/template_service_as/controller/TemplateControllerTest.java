package com.xk.template_service_as.controller;

import com.xk.template_service_as.entity.Field;
import com.xk.template_service_as.entity.FieldToStringConverterTest;
import com.xk.template_service_as.entity.TemplateType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import tools.jackson.databind.ObjectMapper;

import java.util.List;

import static org.hamcrest.Matchers.hasProperty;
import static org.hamcrest.Matchers.hasSize;
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
    void testAddTextField() throws Exception {
        mockMvc.perform(
            post("/templates/create")
                .param("addTextField"))
            .andExpect(status().isOk())
            .andExpect(model().attributeExists("templateDTO"))
            .andExpect(model().attribute("templateDTO", hasProperty("fields", hasSize(1))))
            .andExpect(view().name("home"));
    }

    @Test
    void testCreateFormSuccess() throws Exception {
        mockMvc.perform(
                post("/templates/create")
                    .param("templateName", "Test Template")
                    .param("type", String.valueOf(TemplateType.FORM))
                    .param("fields", fields()))
            .andExpect(status().is3xxRedirection());
    }

    private String fields() {
        List<Field> fields = FieldToStringConverterTest.createSampleFields();
        return fields.stream().map(Field::toDTO).toList().toString();
    }

}
