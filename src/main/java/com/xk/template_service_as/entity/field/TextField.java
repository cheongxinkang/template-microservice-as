package com.xk.template_service_as.entity.field;

import lombok.*;
import tools.jackson.databind.ObjectMapper;

@EqualsAndHashCode(callSuper = true)
@Getter
@Setter
public class TextField extends Field {

    String textValue;

    public TextField() {
        super("", "", FieldType.TEXT);
    }

    public TextField(String prompt, String variableName, String data) {
        super(prompt, variableName, FieldType.TEXT);
        this.textValue = data;
    }

    public TextField(String prompt, String variableName) {
        super(prompt, variableName, FieldType.TEXT);
        this.textValue = "";
    }

    @Override
    public String toString() {
        final ObjectMapper mapper = new ObjectMapper();
        return mapper.writeValueAsString(this);
    }

}
