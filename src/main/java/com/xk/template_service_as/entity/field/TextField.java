package com.xk.template_service_as.entity.field;

import com.xk.template_service_as.dto.FieldDTO;
import lombok.Data;
import lombok.EqualsAndHashCode;
import tools.jackson.databind.ObjectMapper;

@EqualsAndHashCode(callSuper = true)
@Data
public class TextField extends Field {

    String prompt;

    String inputData;

    public TextField(String fieldName, String prompt) {
        super("label", fieldName, FieldType.TEXT);
        this.prompt = prompt;
        this.inputData = "";
    }

    public TextField(String fieldName, String prompt, String inputData) {
        super("label", fieldName, FieldType.TEXT);
        this.prompt = prompt;
        this.inputData = inputData;
    }

    public TextField() {
        super("label","", FieldType.TEXT);
    }

    @Override
    public String toString() {
        final ObjectMapper mapper = new ObjectMapper();
        return mapper.writeValueAsString(this);
    }

    @Override
    public FieldDTO toDTO() {
        return FieldDTO.builder()
            .label(this.label)
            .type(this.type.toString())
            .textValue(this.prompt)
            .build();
    }
}
