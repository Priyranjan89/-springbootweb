package com.learn.spring.boot.web.springbootweb.controller;

import com.learn.spring.boot.web.springbootweb.advices.SuccessResponse;
import com.learn.spring.boot.web.springbootweb.dto.InputDTO;
import com.learn.spring.boot.web.springbootweb.dto.PrimeResponse;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class PrimeController {

    @PostMapping("/check")
    public ResponseEntity<PrimeResponse> checkPrime(@RequestBody @Valid InputDTO input) {
        return ResponseEntity.ok(new PrimeResponse("Valid prime number: " + input.getNumber()));
    }
}