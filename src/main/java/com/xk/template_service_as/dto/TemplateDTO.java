package com.xk.template_service_as.dto;

import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.PastOrPresent;
import lombok.Builder;

import java.util.Date;
import java.util.UUID;

@Builder
public record TemplateDTO(
    UUID id,

    @NotEmpty(message = "Template needs to be named.")
    String templateName,

    @NotEmpty(message = "Template needs to be typed.")
    String type,

    @NotEmpty(message = "An empty template is useless.")
    String fields,

    @PastOrPresent
    Date createdAt,

    @FutureOrPresent
    Date modifiedAt
) { }
