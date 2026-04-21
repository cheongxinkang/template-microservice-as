package com.xk.template_service_as.service;

import com.xk.template_service_as.dto.TemplateDTO;

public interface TemplateService {

    TemplateDTO createTemplate(TemplateDTO templateDTO);

    void addTextField(TemplateDTO templateDTO);

}
