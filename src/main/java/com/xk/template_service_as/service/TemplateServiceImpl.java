package com.xk.template_service_as.service;

import com.xk.template_service_as.dto.FieldDTO;
import com.xk.template_service_as.dto.TemplateConverter;
import com.xk.template_service_as.dto.TemplateDTO;
import com.xk.template_service_as.entity.FieldType;
import com.xk.template_service_as.entity.Template;
import com.xk.template_service_as.entity.fields.TextField;
import com.xk.template_service_as.repository.TemplateRepository;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

@Service
@Qualifier("v1TemplateService")
public class TemplateServiceImpl implements TemplateService {

    private final TemplateRepository templateRepository;
    private final TemplateConverter templateConverter;

    public TemplateServiceImpl(TemplateRepository templateRepository, TemplateConverter templateConverter) {
        this.templateRepository = templateRepository;
        this.templateConverter = templateConverter;
    }

    @Override
    public TemplateDTO createTemplate(@RequestBody TemplateDTO templateDTO) {
        Template savedTemplate = templateRepository.save(templateConverter.toEntity(templateDTO));
        return templateConverter.toDTO(savedTemplate);
    }

    @Override
    public void addTextField(@RequestBody TemplateDTO templateDTO) {
        templateDTO.getFields().add(
            FieldDTO.builder()
                .type(FieldType.TEXT.toString())
                .build()
        );
    }

}
