package com.xk.template_service_as.dto;

import com.xk.template_service_as.entity.Field;
import com.xk.template_service_as.entity.FieldToStringConverter;
import com.xk.template_service_as.entity.Template;
import com.xk.template_service_as.entity.util.FieldParser;
import org.springframework.stereotype.Service;
import tools.jackson.databind.ObjectMapper;

@Service
public class TemplateConverter {

    public TemplateConverter() {
    }

    public Template toEntity(TemplateDTO dto) {
        return Template.builder()
            .id(dto.getId())
            .templateName(dto.getTemplateName())
            .type(dto.getType())
            .fields(dto.getFields()
                .stream()
                .map(FieldDTO::toEntity)
                .toList())
            .createdAt(dto.getCreatedAt())
            .modifiedAt(dto.getModifiedAt())
            .build();
    }

    public TemplateDTO toDTO(Template entity) {
        return TemplateDTO.builder()
            .id(entity.getId())
            .templateName(entity.getTemplateName())
            .type(entity.getType())
            .fields(entity.getFields()
                .stream()
                .map(Field::toDTO)
                .toList())
            .createdAt(entity.getCreatedAt())
            .modifiedAt(entity.getModifiedAt())
            .build();
    }

}
