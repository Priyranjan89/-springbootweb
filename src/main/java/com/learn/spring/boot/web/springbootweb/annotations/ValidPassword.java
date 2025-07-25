package com.learn.spring.boot.web.springbootweb.annotations;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Documented
@Target({ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = PasswordValidator.class)
public @interface ValidPassword {
    String message() default "Invalid password: must contain at least one uppercase letter, one lowercase letter, one special character, and be at least 10 characters long";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
