package com.xk.template_service_as.dto;

import com.xk.template_service_as.entity.Field;
import com.xk.template_service_as.entity.fields.TextField;
import lombok.*;
import tools.jackson.databind.ObjectMapper;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class FieldDTO {

    private String type;
    private String label;
    private String textValue;
    private String numberValue;

    public Field toEntity() {
        if ("TEXT".equalsIgnoreCase(type)) {
            return new TextField(label, textValue);
        }
        throw new IllegalArgumentException("Unknown field type: " + type);
    }

    @Override
    public String toString() {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.writeValueAsString(this);
    }

}
