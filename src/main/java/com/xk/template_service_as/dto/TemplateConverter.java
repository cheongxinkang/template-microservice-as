package com.xk.template_service_as.dto;

import com.xk.template_service_as.entity.FieldConverter;
import com.xk.template_service_as.entity.Template;
import com.xk.template_service_as.entity.TemplateType;
import org.springframework.stereotype.Service;
import tools.jackson.databind.ObjectMapper;

@Service
public class TemplateConverter {

    private final FieldConverter fieldConverter;

    public TemplateConverter(ObjectMapper objectMapper) {
        this.fieldConverter = new FieldConverter(objectMapper);
    }

    public Template toEntity(TemplateDTO dto) {
        return Template.builder()
            .id(dto.id())
            .templateName(dto.templateName())
            .type(TemplateType.valueOf(dto.type()))
            .fields(dto.fields())
            .createdAt(dto.createdAt())
            .modifiedAt(dto.modifiedAt())
            .build();
    }

    public TemplateDTO toDTO(Template entity) {
        return TemplateDTO.builder()
            .id(entity.getId())
            .templateName(entity.getTemplateName())
            .type(entity.getType().toString())
            .fields(entity.getFields())
            .createdAt(entity.getCreatedAt())
            .modifiedAt(entity.getModifiedAt())
            .build();
    }

}
