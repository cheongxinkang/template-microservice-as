package com.xk.template_service_as.entity.fields;

import com.xk.template_service_as.entity.Field;
import com.xk.template_service_as.entity.FieldType;
import lombok.Data;
import lombok.EqualsAndHashCode;
import tools.jackson.databind.ObjectMapper;

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

    public TextField(String fieldName, String prompt, String inputData) {
        super(fieldName, FieldType.TEXT);
        this.prompt = prompt;
        this.inputData = inputData;
    }

    @Override
    public String toString() {
        final ObjectMapper mapper = new ObjectMapper();
        return mapper.writeValueAsString(this);
    }

}
