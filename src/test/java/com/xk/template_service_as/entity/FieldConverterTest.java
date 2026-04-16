package com.xk.template_service_as.entity;

import com.xk.template_service_as.entity.fields.TextField;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import tools.jackson.databind.ObjectMapper;

import java.util.ArrayList;
import java.util.List;


public class FieldConverterTest {

    private final ObjectMapper objectMapper = new  ObjectMapper();
    private final FieldConverter fieldConverter = new FieldConverter(objectMapper);


    @Test
    void testConvertFieldsToString() {
        List<Field> fieldsList = createSampleFields();

        String json = fieldConverter.convertToDatabaseColumn(fieldsList);

        Assertions.assertEquals(sampleFieldsJson(), json);
    }

    @Test
    void testConvertJsonToFields() {
        List<Field> fieldsList = fieldConverter.convertToEntityAttribute(sampleFieldsJson());

        Assertions.assertEquals(createSampleFields(), fieldsList);
    }

    public static List<Field> createSampleFields() {
        List<Field> fieldsList = new ArrayList<>();
        fieldsList.add(new TextField("stayGold", "Do you know Stay Gold?"));
        fieldsList.add(new TextField("inTheEnd", "Do you know Linkin Park?"));
        return fieldsList;
    }

    public static String sampleFieldsJson() {
        return "[{\"fieldName\":\"stayGold\",\"inputData\":\"\",\"prompt\":\"Do you know Stay Gold?\",\"type\":\"TEXT\"},{\"fieldName\":\"inTheEnd\",\"inputData\":\"\",\"prompt\":\"Do you know Linkin Park?\",\"type\":\"TEXT\"}]";
    }

}
