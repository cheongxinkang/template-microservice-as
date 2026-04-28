package com.xk.template_service_as.entity.field;

import lombok.EqualsAndHashCode;
import tools.jackson.databind.ObjectMapper;

@EqualsAndHashCode(callSuper = true)
public class NumericalField extends Field {

    double realNumber;

    // Empty constructor necessary for ObjectMapper
    public NumericalField() {
        super("", "", FieldType.NUMERICAL);
    }

    public NumericalField(String prompt, String variableName, double data) {
        super(prompt, variableName, FieldType.NUMERICAL);
        this.realNumber = data;
    }

    public int getIntValue() {
        return (int) realNumber;
    }

    public double getRealNumber() {
        return realNumber;
    }

    public void setRealNumber(double realNumber) {
        this.realNumber = realNumber;
    }

    public void setRealNumber(int intNumber) {
        this.realNumber = intNumber;
    }

    @Override
    public String toString() {
        final ObjectMapper mapper = new ObjectMapper();
        return mapper.writeValueAsString(this);
    }



}
