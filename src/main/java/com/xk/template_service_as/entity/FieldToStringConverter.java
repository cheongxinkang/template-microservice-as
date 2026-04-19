package com.xk.template_service_as.entity;

import com.xk.template_service_as.entity.fields.TextField;
import com.xk.template_service_as.entity.util.FieldParser;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;
import tools.jackson.databind.JsonNode;
import tools.jackson.databind.ObjectMapper;

import java.util.ArrayList;
import java.util.List;

@Converter
public class FieldToStringConverter implements AttributeConverter<List<Field>, String> {

    private final ObjectMapper objectMapper;

    private final FieldParser fieldParser;

    public FieldToStringConverter(ObjectMapper objectMapper, FieldParser fieldParser) {
        this.objectMapper = objectMapper;
        this.fieldParser = fieldParser;
    }

    @Override
    public String convertToDatabaseColumn(List<Field> fields) {
        return objectMapper.writeValueAsString(fields);
    }

    @Override
    public List<Field> convertToEntityAttribute(String s) {
        return fieldParser.parseStringToListField(s);
    }
}
