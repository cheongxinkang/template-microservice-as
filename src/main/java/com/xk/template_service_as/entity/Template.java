package com.xk.template_service_as.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name="templates")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Template {

    @Id @GeneratedValue
    private UUID id;

    @Column
    private String templateName;

    @Column
    private TemplateType type;

    @Convert(converter = FieldConverter.class)
    private List<Field> fields;

    @Column
    private Date createdAt;

    @Column
    private Date modifiedAt;

}
