package com.learn.spring.boot.web.springbootweb.dto;

import com.learn.spring.boot.web.springbootweb.annotations.Prime;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class InputDTO {
    @Prime(message = "Please enter a prime number only")
    private Integer number;

}
