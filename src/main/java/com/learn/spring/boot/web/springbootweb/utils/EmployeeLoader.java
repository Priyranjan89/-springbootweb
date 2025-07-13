package com.learn.spring.boot.web.springbootweb.utils;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.learn.spring.boot.web.springbootweb.dto.EmployeeDTO;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
public class EmployeeLoader {

    public Map<Long, EmployeeDTO> loadEmployees() {
        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule()); // 👈 Register module for LocalDate
        mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS); // Optional: for ISO format

        ClassPathResource resource = new ClassPathResource("employee.json");

        try (InputStream inputStream = resource.getInputStream()) {
            List<EmployeeDTO> employees = mapper.readValue(inputStream, new TypeReference<List<EmployeeDTO>>() {});
            return employees.stream().collect(Collectors.toMap(EmployeeDTO::getId, e -> e));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
