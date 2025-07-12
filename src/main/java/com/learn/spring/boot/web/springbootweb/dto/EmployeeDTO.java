package com.learn.spring.boot.web.springbootweb.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;


import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class EmployeeDTO {

    private Long id;

    @NotBlank(message = "Name of employee cannot be blank")
    @NotNull(message = "Required field in Employee: name")
    @NotEmpty(message = "Name of employee cannot be blank")
    @Size(min = 3, max = 20, message = "Number of characters in name should be in the range: [3, 20]")
    private String name;

    @Email(message = "Email should be valid email")
    private String email;

    @Max(value = 80, message = "Age of employee cannot be greater than 80")
    @Min(value = 18, message = "Age of employee cannot be less than 18")
    private Integer age;

    @JsonFormat(pattern = "yyyy-MM-dd")
    @PastOrPresent(message = "Date Of Joining of employee cannot be future")
    private LocalDate dateOfJoining;

    @NotBlank(message = "IsActive of employee cannot be blank")
    @NotNull(message = "Required field in Employee: isActive")
    @NotEmpty(message = "IsActive of employee cannot be blank")
    private Boolean isActive;

    @NotBlank(message = "Role of employee cannot be blank")
    @NotEmpty(message = "Role of employee cannot be null")
    @Pattern(regexp = "^(ADMIN|USER)$", message = "Role of employee should be either USER or ADMIN")
    private String role;

    @NotNull(message = "Salary of employee cannot be null")
    @Positive(message = "Salary of employee should be positive")
    @Digits(integer = 6, fraction = 2, message = "The salary can be in the form xxxxx.yy")
    @DecimalMax(value = "1000000", message = "Salary of employee cannot be more than 1000000")
    @DecimalMin(value = "10000", message = "Salary of employee cannot be less than 10000")
    private Double salary;
}
