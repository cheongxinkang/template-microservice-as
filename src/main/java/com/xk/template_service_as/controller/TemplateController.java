package com.xk.template_service_as.controller;

import com.xk.template_service_as.dto.TemplateDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/templates")
public class TemplateController {

    public TemplateController() {

    }

    @PostMapping("/create")
    public ResponseEntity<TemplateDTO> createTemplate(@RequestBody TemplateDTO templateDTO) {
        return ResponseEntity.ok(templateDTO);
    }

}
