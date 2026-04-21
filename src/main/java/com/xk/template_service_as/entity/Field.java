package com.xk.template_service_as.entity;

import com.xk.template_service_as.dto.FieldDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import tools.jackson.databind.ObjectMapper;

@Data
@AllArgsConstructor
@NoArgsConstructor
public abstract class Field {

    protected String label;

    protected String fieldName;

    protected FieldType type;

    public abstract FieldDTO toDTO();

}
