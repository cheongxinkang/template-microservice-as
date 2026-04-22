package com.xk.template_service_as.util;

import com.xk.template_service_as.dto.FieldDTO;
import com.xk.template_service_as.entity.field.Field;
import com.xk.template_service_as.entity.field.TextField;
import org.springframework.stereotype.Component;
import tools.jackson.databind.JsonNode;
import tools.jackson.databind.ObjectMapper;

import java.util.List;

@Component
public class FieldsParser {

    private ObjectMapper objectMapper;

    public FieldsParser(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    public String toJson(List<Field> fields) {
        try {
            return objectMapper.writeValueAsString(fields);
        }  catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<FieldDTO> toFieldDTOList(String s) {
        List<JsonNode> fieldNodes = toJsonNodes(s);
        return fieldNodes.stream().map(this::parseFieldDTO).toList();
    }

    public List<Field> toFieldList(String s) {
        List<JsonNode> fieldNodes = toJsonNodes(s);
        return fieldNodes.stream().map(this::parseField).toList();
    }

    private List<JsonNode> toJsonNodes(String s) {
        JsonNode root =  objectMapper.readTree(s);
        return root.valueStream().toList();
    }

    private FieldDTO parseFieldDTO(JsonNode fieldDTO) {
        return objectMapper.readValue(fieldDTO.toString(), FieldDTO.class);
    }

    private Field parseField(JsonNode field) {
        String type = field.get("type").asString();

        switch(type) {
            case "TEXT":
                return new TextField(
                    field.get("fieldName").asString(),
                    field.get("prompt").asString()
                );
        }

        return null;
    }

}
