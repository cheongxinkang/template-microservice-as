package com.xk.template_service_as.controller;

import com.xk.template_service_as.dto.TemplateDTO;
import com.xk.template_service_as.service.TemplateService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@Controller
@RequestMapping("/templates")
@Validated
public class TemplateController {

    private static final String TEMPLATE_FORM = "home";

    TemplateService templateService;

    public TemplateController(
        @Qualifier("v1TemplateService") TemplateService templateService) {
        this.templateService = templateService;
    }

    @InitBinder
    public void setAllowedFields(WebDataBinder dataBinder) {
        dataBinder.setDisallowedFields("id");
    }

    @ModelAttribute("templateDTO")
    public TemplateDTO createBlankTemplate() {
        return TemplateDTO.builder().build();
    }

    @GetMapping("/create")
    public String getForm() {
        return TEMPLATE_FORM;
    }

    @PostMapping("/create")
    public String createTemplate(@Valid TemplateDTO templateDTO, BindingResult result,
                                 RedirectAttributes redirectAttributes) {
        if (result.hasErrors()) {
            redirectAttributes.addFlashAttribute("error", "There was an error in creating the template.");
            return TEMPLATE_FORM;
        }

        // save to repository
        redirectAttributes.addFlashAttribute("message", "New Template created.");
        return "redirect:/templates/" + templateDTO.getId();
    }

}
