package com.learn.spring.boot.web.springbootweb.controller;

import com.learn.spring.boot.web.springbootweb.dto.EmployeeDTO;
import com.learn.spring.boot.web.springbootweb.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/employees")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @GetMapping("/{employeeId}")
    public EmployeeDTO getEmployeeById(@PathVariable Long employeeId){
        return employeeService.getEmployeeById(employeeId);
    }

  /*  @GetMapping("/employees")
    public List<EmployeeDTO> getEmployees(){

        return employeeService.getAllEmployees();
    }*/

    @GetMapping
    public ResponseEntity<List<EmployeeDTO>> getAllEmployees(@RequestParam(required = false, name = "age") Integer age,
                                                             @RequestParam(required = false) String sortBy) {
        return ResponseEntity.ok(employeeService.getAllEmployees());
    }

    @PostMapping
    public EmployeeDTO createEmployee(@RequestBody EmployeeDTO employee){
        return employeeService.createEmployee(employee);
    }
}
