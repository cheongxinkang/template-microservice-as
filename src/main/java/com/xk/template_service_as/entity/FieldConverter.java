package com.xk.template_service_as.entity;

import com.xk.template_service_as.entity.fields.TextField;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;
import tools.jackson.databind.JsonNode;
import tools.jackson.databind.ObjectMapper;

import java.util.ArrayList;
import java.util.List;

@Converter
public class FieldConverter implements AttributeConverter<List<Field>, String> {

    private final ObjectMapper objectMapper;

    public FieldConverter(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    @Override
    public String convertToDatabaseColumn(List<Field> fields) {
        return objectMapper.writeValueAsString(fields);
    }

    @Override
    public List<Field> convertToEntityAttribute(String s) {
        List<Field> fieldsList = new ArrayList<>();
        JsonNode root =  objectMapper.readTree(s);

        for (JsonNode child : root.asArray()) {
            fieldsList.add(parseField(child));
        }


        return fieldsList;
    }

    private Field parseField(JsonNode field) {
        FieldType type = FieldType.valueOf(field.get("type").stringValue());

        switch (type) {
            case type.TEXT:
                return new TextField(
                    field.get("fieldName").stringValue(),
                    field.get("prompt").stringValue(),
                    field.get("inputData").stringValue());
        }

        return null;
    }
}
