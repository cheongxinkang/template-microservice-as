package com.xk.template_service_as.entity.field;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.EqualsAndHashCode;
import tools.jackson.databind.ObjectMapper;

import java.time.LocalDateTime;

@EqualsAndHashCode(callSuper = true)
public class DateTimeField extends Field {

    @JsonProperty("dateTimeValue")
    LocalDateTime dateTime;

    // Empty constructor necessary for ObjectMapper
    public DateTimeField() {
        super("", "", FieldType.DATE_TIME);
    }

    public DateTimeField(String prompt, String variableName, LocalDateTime data) {
        super(prompt, variableName, FieldType.DATE_TIME);
        this.dateTime = data;
    }

    public LocalDateTime getDateTime() {
        return this.dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    @Override
    public String toString() {
        final ObjectMapper mapper = new ObjectMapper();
        return mapper.writeValueAsString(this);
    }

}
