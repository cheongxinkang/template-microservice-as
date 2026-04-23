package com.xk.template_service_as.service;

import com.xk.template_service_as.dto.FieldDTO;
import com.xk.template_service_as.entity.field.Field;
import com.xk.template_service_as.entity.field.TextField;
import org.springframework.stereotype.Service;
import tools.jackson.databind.JsonNode;
import tools.jackson.databind.ObjectMapper;

import java.util.List;

@Service
public class FieldsParsingService {

    private ObjectMapper objectMapper;

    public FieldsParsingService(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    public String toJson(List<Field> fields) {
        try {
            return objectMapper.writeValueAsString(fields);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<FieldDTO> toFieldDTOList(String s) {
        List<JsonNode> fieldNodes = toJsonNodes(s);
        return fieldNodes.stream()
            .map(node -> (FieldDTO) parseTest(node, FieldDTO.class))
            .toList();
    }

    public List<Field> toFieldList(String s) {
        List<JsonNode> fieldNodes = toJsonNodes(s);
        return fieldNodes.stream()
            .map(node -> (Field) parseTest(node, Field.class))
            .toList();
    }

    public List<Field> toFieldList(List<FieldDTO> fieldDTOList) {
        return fieldDTOList.stream()
            .map(fieldDTO -> (Field) parseTest(fieldDTO, Field.class))
            .toList();
    }

    public List<FieldDTO> toFieldDtoList(List<Field> fieldList) {
        return fieldList.stream()
            .map(field -> (FieldDTO) parseTest(field, FieldDTO.class))
            .toList();
    }

    private List<JsonNode> toJsonNodes(String s) {
        JsonNode root = objectMapper.readTree(s);
        return root.valueStream().toList();
    }

    private Object parseTest(Object objectToBeParsed, Class c) {
        if (c != FieldDTO.class) {
            String type = "";
            if (objectToBeParsed instanceof JsonNode) {
                type = ((JsonNode) objectToBeParsed).get("type").asString();
            } else if (objectToBeParsed instanceof FieldDTO) {
                type = ((FieldDTO) objectToBeParsed).getType();
            }

            switch (type) {
                case "TEXT":
                    c = TextField.class;
            }
        }

        return objectMapper.convertValue(objectToBeParsed, c);
    }

}
