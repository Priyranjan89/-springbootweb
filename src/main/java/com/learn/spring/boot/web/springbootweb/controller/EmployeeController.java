package com.learn.spring.boot.web.springbootweb.controller;

import com.learn.spring.boot.web.springbootweb.dto.EmployeeDTO;
import com.learn.spring.boot.web.springbootweb.exceptions.ResourceNotFoundException;
import com.learn.spring.boot.web.springbootweb.service.EmployeeService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

@RestController
@RequestMapping("/employees")
public class EmployeeController {

    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping("/{employeeId}")
    public ResponseEntity<EmployeeDTO> getEmployeeById(@PathVariable Long employeeId) {
        Optional<EmployeeDTO> employeeDTO = employeeService.getEmployeeById(employeeId);
        return employeeDTO.map(employeeDTO1 -> ResponseEntity.ok(employeeDTO1))
                .orElseThrow(() -> new ResourceNotFoundException("Employee not found with id: "+ employeeId));
    }

    @GetMapping
    public ResponseEntity<List<EmployeeDTO>> getAllEmployees(@RequestParam(required = false, name = "age") Integer age,
                                                             @RequestParam(required = false) String sortBy) {
        return ResponseEntity.ok((List<EmployeeDTO>) employeeService.getAllEmployees());
    }

    @PostMapping
    public ResponseEntity<EmployeeDTO> createEmployee(@RequestBody @Valid EmployeeDTO employeeDTO) {
        EmployeeDTO saveEmployee = employeeService.createEmployee(employeeDTO);
        return new ResponseEntity<>(saveEmployee, HttpStatus.CREATED);
    }

    @PostMapping("/listOfEmployee")
    public  ResponseEntity<List<EmployeeDTO>> createAllEmployees(@RequestBody @Valid List<EmployeeDTO> employeeDTOS) {
        return new ResponseEntity<>(employeeService.createAllEmployees(employeeDTOS), HttpStatus.CREATED);
    }

    @PutMapping("/{employeeId}")
    public ResponseEntity<EmployeeDTO> updateEmployeeById(@RequestBody EmployeeDTO employeeDTO, @PathVariable Long employeeId) {
        return ResponseEntity.ok(employeeService.updateEmployeeById(employeeDTO, employeeId));
    }

    @DeleteMapping("/{employeeId}")
    public ResponseEntity<Boolean> deleteEmployeeById(@PathVariable Long employeeId) {
        Boolean isDeleted = employeeService.deleteEmployeeById(employeeId);
        if(isDeleted) return ResponseEntity.ok(true);
        return ResponseEntity.notFound().build();
    }

    @PatchMapping("/{employeeId}")
    public ResponseEntity<EmployeeDTO> updatePartialEmployeeById(@RequestBody Map<String, Object> updates, @PathVariable Long employeeId) {
        EmployeeDTO employeeDTO = employeeService.updatePartialEmployeeById(updates, employeeId);
        if (Objects.isNull(employeeDTO)) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(employeeDTO);
    }

}
