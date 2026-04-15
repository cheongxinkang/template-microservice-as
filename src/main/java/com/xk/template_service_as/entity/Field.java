package com.xk.template_service_as.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public abstract class Field {

    protected String fieldName;

    protected FieldType type;

}
