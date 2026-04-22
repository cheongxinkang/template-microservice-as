package com.xk.template_service_as.entity.util;

import com.xk.template_service_as.dto.FieldDTO;
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

    public List<FieldDTO> parseStringToListFieldDTO(String s) {
        List<FieldDTO> fieldsList = new ArrayList<>();
        JsonNode root =  objectMapper.readTree(s);

        for (JsonNode child : root.asArray()) {
            fieldsList.add(parseFieldDTO(child));
        }


        return fieldsList;
    }

    private FieldDTO parseFieldDTO(JsonNode fieldDTO) {
        return objectMapper.readValue(fieldDTO.toString(), FieldDTO.class);
    }

}
