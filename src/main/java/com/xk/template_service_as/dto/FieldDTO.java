package com.xk.template_service_as.dto;

import com.xk.template_service_as.entity.field.Field;
import com.xk.template_service_as.entity.field.TextField;
import lombok.*;
import tools.jackson.databind.ObjectMapper;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class FieldDTO {

    private String type;
    private String prompt;
    private String variableName;
    private String textValue;
    private String numberValue;

    @Override
    public String toString() {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.writeValueAsString(this);
    }

}
