package com.learn.spring.boot.web.springbootweb.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
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
public class DepartmentDTO {

    private Long id;

    @NotBlank(message = "Department title should not blank")
    @NotNull(message = "Department title should not null")
    @NotEmpty(message = "Department title should not Empty")
    @Size(min = 2, max = 30, message = "Number of characters in name should be in the range: [2, 30]")
    private String title;

    @NotNull(message = "Required field in Department: isActive")
    private Boolean isActive;

    @JsonFormat(pattern = "yyyy-MM-dd")
    @PastOrPresent(message = "Date Of createdAt of employee cannot be future")
    private LocalDate createdAt;
}
