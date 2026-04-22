package com.xk.template_service_as.entity;

import com.xk.template_service_as.dto.FieldDTO;
import com.xk.template_service_as.entity.util.FieldParser;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class StringToFieldDTOConverter implements Converter<String, List<FieldDTO>> {

    private final FieldParser fieldParser;

    public StringToFieldDTOConverter(FieldParser fieldParser) {
        this.fieldParser = fieldParser;
    }

    @Override
    public List<FieldDTO> convert(String source) {
        return fieldParser.parseStringToListFieldDTO(source);
    }
}
