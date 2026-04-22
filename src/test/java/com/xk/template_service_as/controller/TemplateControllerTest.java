package com.xk.template_service_as.controller;

import com.xk.template_service_as.dto.FieldDTO;
import com.xk.template_service_as.entity.field.Field;
import com.xk.template_service_as.entity.FieldAttributeConverterTest;
import com.xk.template_service_as.entity.TemplateType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import tools.jackson.databind.ObjectMapper;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.hasProperty;
import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
public class TemplateControllerTest {

    private final WebApplicationContext context;

    @Autowired
    TemplateControllerTest(WebApplicationContext context) {
        this.context = context;
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
                    .param("fields", fieldDTOs()))
            .andExpect(status().is3xxRedirection());
    }

    private String fieldDTOs() {
        List<FieldDTO> fieldDTOs = new ArrayList<>();

        fieldDTOs.add(FieldDTO.builder()
            .type("TEXT")
            .prompt("This is a question.")
            .variableName("testVar1")
            .build());

        return fieldDTOs.toString();
    }

}
