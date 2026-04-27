package com.xk.template_service_as.service;

import com.xk.template_service_as.dto.FieldRow;
import com.xk.template_service_as.dto.TemplateDTO;

public interface TemplateService {

    TemplateDTO saveTemplate(TemplateDTO templateDTO);

    TemplateDTO createOrReplaceFields(TemplateDTO templateDTO, FieldRow[] fieldRows);

    TemplateDTO createEmptyTemplate();

}
