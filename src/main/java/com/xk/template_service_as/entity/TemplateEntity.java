package com.xk.template_service_as.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name="templates")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TemplateEntity {

    @Id @GeneratedValue
    private UUID id;

    @Convert(converter = FieldConverter.class)
    private List<Field> fields = new ArrayList<>();

}
