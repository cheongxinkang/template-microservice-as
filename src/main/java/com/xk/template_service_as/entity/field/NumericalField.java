package com.xk.template_service_as.entity.field;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.EqualsAndHashCode;
import tools.jackson.databind.ObjectMapper;

@EqualsAndHashCode(callSuper = true)
public class NumericalField extends Field {

    @JsonProperty("numberValue")
    double realNumber;

    // Empty constructor necessary for ObjectMapper
    public NumericalField() {
        super("", "", FieldType.NUMERICAL);
    }

    public NumericalField(String prompt, String variableName, double data) {
        super(prompt, variableName, FieldType.NUMERICAL);
        this.realNumber = data;
    }

    public double getRealNumber() {
        return realNumber;
    }

    public void setRealNumber(double realNumber) {
        this.realNumber = realNumber;
    }

    @Override
    public String toString() {
        final ObjectMapper mapper = new ObjectMapper();
        return mapper.writeValueAsString(this);
    }



}
