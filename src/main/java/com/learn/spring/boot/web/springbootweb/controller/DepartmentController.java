package com.learn.spring.boot.web.springbootweb.controller;

import com.learn.spring.boot.web.springbootweb.dto.DepartmentDTO;
import com.learn.spring.boot.web.springbootweb.exceptions.DepartmentNotFoundException;
import com.learn.spring.boot.web.springbootweb.service.DepartmentService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/departments")
public class DepartmentController {

    private final DepartmentService departmentService;

    public DepartmentController(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    @GetMapping("/{departmentId}")
    public ResponseEntity<DepartmentDTO> getDepartment(@PathVariable Long departmentId){
        Optional<DepartmentDTO> departmentDTO = departmentService.getDepartmentById(departmentId);
        return departmentDTO.map(department -> ResponseEntity.ok(department))
                .orElseThrow(() -> new DepartmentNotFoundException("Department is not with Id: "+departmentId ));
    }

    @GetMapping
    public ResponseEntity<List<DepartmentDTO>> getDepartments(){
       return ResponseEntity.ok((List<DepartmentDTO>) departmentService.getAllDepartment());
    }

    @PostMapping
    public ResponseEntity<DepartmentDTO> createDepartment(@RequestBody @Valid DepartmentDTO departmentDTO){
        return new ResponseEntity<>(departmentService.createDepartment(departmentDTO), HttpStatus.CREATED);
    }

    @PutMapping("/{departmentId}")
    public ResponseEntity<DepartmentDTO>updateDepartment(@RequestBody @Valid DepartmentDTO departmentDTO, @PathVariable Long departmentId){
        return ResponseEntity.ok(departmentService.updateDepartment(departmentDTO, departmentId));
    }

    @DeleteMapping("/{departmentId}")
    public ResponseEntity<Boolean> deleteDepartmentById(@PathVariable Long departmentId) {
        Boolean isDeleted = departmentService.deleteDepartmentById(departmentId);
        if(isDeleted) return ResponseEntity.ok(true);
        return ResponseEntity.notFound().build();
    }
}
