package com.xk.template_service_as.dto;

import com.xk.template_service_as.entity.Field;
import com.xk.template_service_as.entity.fields.TextField;
import lombok.*;

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
        } else if ("NUMBER".equalsIgnoreCase(type)) {
//            return new NumberField(label, numberValue);
        }
        throw new IllegalArgumentException("Unknown field type: " + type);
    }

}
