package com.xk.template_service_as.dto;

import com.xk.template_service_as.util.FieldsParser;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class FieldDTOConverter implements Converter<String, List<FieldDTO>> {

    private final FieldsParser fieldsParser;

    public FieldDTOConverter(FieldsParser fieldsParser) {
        this.fieldsParser = fieldsParser;
    }

    @Override
    public List<FieldDTO> convert(String source) {
        return fieldsParser.toFieldDTOList(source);
    }
}
