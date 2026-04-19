package com.xk.template_service_as.entity.util;

import com.xk.template_service_as.entity.Field;
import com.xk.template_service_as.entity.FieldType;
import com.xk.template_service_as.entity.fields.TextField;
import org.springframework.stereotype.Component;
import tools.jackson.databind.JsonNode;
import tools.jackson.databind.ObjectMapper;

import java.util.ArrayList;
import java.util.List;

@Component
public class FieldParser {

    private ObjectMapper objectMapper;

    public FieldParser(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    public List<Field> parseStringToListField(String s) {
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
            case TEXT:
                return new TextField(
                    field.get("fieldName").stringValue(),
                    field.get("prompt").stringValue(),
                    field.get("inputData").stringValue());
        }

        return null;
    }

}
