package com.xk.template_service_as.entity.field;

import com.xk.template_service_as.util.FieldsParser;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;
import tools.jackson.databind.JsonNode;
import tools.jackson.databind.ObjectMapper;

import java.util.ArrayList;
import java.util.List;

@Converter
public class FieldAttributeConverter implements AttributeConverter<List<Field>, String> {

    private final FieldsParser fieldsParser;

    public FieldAttributeConverter(FieldsParser fieldsParser) {
        this.fieldsParser = fieldsParser;
    }

    @Override
    public String convertToDatabaseColumn(List<Field> fields) {
        return fieldsParser.toJson(fields);
    }

    @Override
    public List<Field> convertToEntityAttribute(String s) {
        return fieldsParser.toFieldList(s);
    }

}
