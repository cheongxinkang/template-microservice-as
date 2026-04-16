package com.xk.template_service_as.controller;

import com.xk.template_service_as.dto.TemplateDTO;
import com.xk.template_service_as.service.TemplateService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/templates")
@Validated
public class TemplateController {

    TemplateService templateService;

    public TemplateController(
        @Qualifier("v1TemplateService") TemplateService templateService) {
        this.templateService = templateService;
    }

    @PostMapping("/create")
    public ResponseEntity<TemplateDTO> createTemplate(@Valid @RequestBody TemplateDTO templateDTO) {
        TemplateDTO createdTemplate = templateService.createTemplate(templateDTO);
        URI location =  ServletUriComponentsBuilder.fromCurrentRequest()
            .path("/{id}")
            .buildAndExpand("123")
            .toUri();

        return ResponseEntity.created(location).body(createdTemplate);
    }

}
