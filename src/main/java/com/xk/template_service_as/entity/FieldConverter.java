package com.xk.template_service_as.entity;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

import java.util.List;

@Converter
public class FieldConverter implements AttributeConverter<List<Field>, String> {

    @Override
    public String convertToDatabaseColumn(List<Field> fields) {
        return null;
    }

    @Override
    public List<Field> convertToEntityAttribute(String s) {
        return null;
    }
}
