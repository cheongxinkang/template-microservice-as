package com.xk.template_service_as.entity;

import com.xk.template_service_as.entity.util.FieldParser;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class StringToFieldConverter implements Converter<String, List<Field>> {

    private final FieldParser fieldParser;

    public StringToFieldConverter(FieldParser fieldParser) {
        this.fieldParser = fieldParser;
    }

    @Override
    public List<Field> convert(String source) {
        return fieldParser.parseStringToListField(source);
    }
}
