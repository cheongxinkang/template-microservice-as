package com.xk.template_service_as.service;

import com.xk.template_service_as.dto.FieldDTO;
import com.xk.template_service_as.entity.field.Field;
import com.xk.template_service_as.entity.field.TextField;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import tools.jackson.databind.ObjectMapper;

import java.util.ArrayList;
import java.util.List;

public class FieldParsingServiceTest {

    ObjectMapper objectMapper = new ObjectMapper();
    FieldsParsingService fieldParsingService = new FieldsParsingService(objectMapper);

    @Test
    void testToDTOListFromString() {
    }

    @Test
    void testToEntityListFromString() {
    }

    @Test
    void testToDTOListFromEntityList() {
        List<FieldDTO> fieldDTOS = fieldParsingService.toFieldDtoList(fields());

        Assertions.assertEquals(fieldDTOs().toString(), fieldDTOS.toString());
    }

    @Test
    void testToEntityListFromDTOList() {
        List<Field> fields = fieldParsingService.toFieldList(fieldDTOs());

        Assertions.assertEquals(fields(), fields);
    }

    @Test
    void testToJson() {
        String json = fieldParsingService.toJson(fields());

        Assertions.assertEquals(json(), json);
    }

    private String json() {
        return "[{\"prompt\":\"This is a sample question.\",\"textData\":null,\"type\":\"TEXT\",\"variableName\":\"questionA\"}]";
    }

    private List<FieldDTO> fieldDTOs() {
        List<FieldDTO> fieldDTOs = new ArrayList<>();

        fieldDTOs.add(FieldDTO.builder()
            .type("TEXT")
            .variableName("questionA")
            .prompt("This is a sample question.")
            .textValue("Answer X")
            .build());

        return fieldDTOs;
    }

    private List<Field> fields() {
        List<Field> fields = new ArrayList<>();

        fields.add(new TextField(
            "This is a sample question.",
            "questionA",
            "Answer X"
        ));

        return fields;
    }

}
