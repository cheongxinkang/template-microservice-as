package com.xk.template_service_as.entity.fields;

import com.xk.template_service_as.entity.Field;
import com.xk.template_service_as.entity.FieldType;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class TextField extends Field {

    String prompt;

    String inputData;

    public TextField(String fieldName, String prompt) {
        super(fieldName, FieldType.TEXT);
        this.prompt = prompt;
        this.inputData = "";
    }

}
