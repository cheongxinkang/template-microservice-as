package com.xk.template_service_as.service;

import com.xk.template_service_as.dto.FieldDTO;
import com.xk.template_service_as.entity.field.Field;
import com.xk.template_service_as.entity.field.TextField;
import org.springframework.stereotype.Service;
import tools.jackson.databind.JsonNode;
import tools.jackson.databind.ObjectMapper;

import java.util.ArrayList;
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
        return fieldNodes.stream().map(this::parseFieldDTO).toList();
    }

    public List<Field> toFieldList(String s) {
        List<JsonNode> fieldNodes = toJsonNodes(s);
        return fieldNodes.stream().map(this::parseField).toList();
    }

    private List<JsonNode> toJsonNodes(String s) {
        JsonNode root = objectMapper.readTree(s);
        return root.valueStream().toList();
    }

    private FieldDTO parseFieldDTO(JsonNode fieldDTO) {
        return objectMapper.readValue(fieldDTO.toString(), FieldDTO.class);
    }

    private Field parseField(JsonNode field) {
        String type = field.get("type").asString();

        switch (type) {
            case "TEXT":
                return objectMapper.convertValue(field, TextField.class);
        }

        return null;
    }

    public List<Field> toFieldList(List<FieldDTO> fieldDTOList) {
        List<Field> fields = new ArrayList<>();

        for (FieldDTO fieldDTO : fieldDTOList) {
            String type = fieldDTO.getType();

            switch (type) {
                case "TEXT":
                    fields.add(objectMapper.convertValue(fieldDTO, TextField.class));
                    break;
            }
        }

        return fields;
    }

    public List<FieldDTO> toFieldDtoList(List<Field> fieldList) {
        List<FieldDTO> fieldDTOS = new ArrayList<>();

        for (Field field : fieldList) {
            String type = field.getType().toString();

            switch (type) {
                case "TEXT":
                    fieldDTOS.add(objectMapper.convertValue(field, FieldDTO.class));
                    break;
            }

        }

        return fieldDTOS;
    }

}
