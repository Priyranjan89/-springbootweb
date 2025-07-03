package com.learn.spring.boot.web.springbootweb.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "employees")
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private String email;
    private Integer age;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate dateOfJoining;
    private Boolean isActive;

    public Employee(){}
}
