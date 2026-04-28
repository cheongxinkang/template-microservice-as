package com.xk.template_service_as.service;

import com.xk.template_service_as.dto.FieldDTO;
import com.xk.template_service_as.entity.field.DateTimeField;
import com.xk.template_service_as.entity.field.Field;
import com.xk.template_service_as.entity.field.NumericalField;
import com.xk.template_service_as.entity.field.TextField;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import tools.jackson.databind.ObjectMapper;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class FieldParsingServiceTest {

    ObjectMapper objectMapper = new ObjectMapper();
    FieldsParsingService fieldParsingService = new FieldsParsingService(objectMapper);

    @Test
    void testToDTOListFromString() {
        List<FieldDTO> fieldDTOs = fieldParsingService.toFieldDTOList(json());

        for (int i = 0; i < fieldDTOs.size(); i++) {
            assertEquals(fieldDTOs().get(i).getClass(), fieldDTOs.get(i).getClass());
            assertEquals(fieldDTOs().get(i).toString(), fieldDTOs.get(i).toString());
        }
    }

    @Test
    void testToEntityListFromString() {
        List<Field> fields = fieldParsingService.toFieldList(json());

        assertEquals(fields(), fields);
    }

    @Test
    void testToDTOListFromEntityList() {
        List<FieldDTO> fieldDTOs = fieldParsingService.toFieldDtoList(fields());

        for (int i = 0; i < fieldDTOs.size(); i++) {
            assertEquals(fieldDTOs().get(i).getClass(), fieldDTOs.get(i).getClass());
            assertEquals(fieldDTOs().get(i).toString(), fieldDTOs.get(i).toString());
        }
    }

    @Test
    void testToEntityListFromDTOList() {
        List<Field> fields = fieldParsingService.toFieldList(fieldDTOs());

        assertEquals(fields(), fields);
    }

    @Test
    void testToJson() {
        String json = fieldParsingService.toJson(fields());

        assertEquals(json(), json);
    }

    private String json() {
        return "[{\"prompt\":\"This is a sample question.\",\"variableName\":\"questionA\",\"textValue\":\"Answer X\",\"type\":\"TEXT\"},{\"prompt\":\"This is a sample number.\",\"variableName\":\"numberA\",\"numberValue\":5.0,\"type\":\"NUMERICAL\"},{\"prompt\":\"This is for date time.\",\"variableName\":\"dateTimeA\",\"dateTimeValue\":\"2023-12-31T10:15:30\",\"type\":\"DATE_TIME\"}]";
    }


    private List<FieldDTO> fieldDTOs() {
        List<FieldDTO> fieldDTOs = new ArrayList<>();

        fieldDTOs.add(FieldDTO.builder()
            .type("TEXT")
            .variableName("questionA")
            .prompt("This is a sample question.")
            .textValue("Answer X")
            .build());

        fieldDTOs.add(FieldDTO.builder()
            .type("NUMERICAL")
            .variableName("numberA")
            .prompt("This is a sample number.")
            .numberValue("5.0")
            .build());

        fieldDTOs.add(FieldDTO.builder()
            .type("DATE_TIME")
            .variableName("dateTimeA")
            .prompt("This is for date time.")
            .dateTimeValue(LocalDateTime.parse("2023-12-31T10:15:30").toString())
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

        fields.add(new NumericalField(
            "This is a sample number.",
            "numberA",
            5.0
        ));

        fields.add(new DateTimeField(
            "This is for date time.",
            "dateTimeA",
            LocalDateTime.parse("2023-12-31T10:15:30")
        ));

        return fields;
    }

}
