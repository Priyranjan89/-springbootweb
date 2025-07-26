package com.learn.spring.boot.web.springbootweb.service;

import com.learn.spring.boot.web.springbootweb.dto.DepartmentDTO;
import com.learn.spring.boot.web.springbootweb.entities.Department;
import com.learn.spring.boot.web.springbootweb.exceptions.DepartmentNotFoundException;
import com.learn.spring.boot.web.springbootweb.repositories.DepartmentRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class DepartmentService {

    private final DepartmentRepository departmentRepository;
    private final ModelMapper modelMapper;

    public DepartmentService(DepartmentRepository departmentRepository, ModelMapper modelMapper) {
        this.departmentRepository = departmentRepository;
        this.modelMapper = modelMapper;
    }

    public Optional<DepartmentDTO> getDepartmentById(Long departmentId) {
        return departmentRepository.findById(departmentId)
                .map(department -> modelMapper.map(department, DepartmentDTO.class));
    }


    public List<DepartmentDTO> getAllDepartment() {
        List<Department> departments = departmentRepository.findAll();

        return departments.stream()
                .map(department -> modelMapper.map(department, DepartmentDTO.class))
                .collect(Collectors.toList());
    }

    public DepartmentDTO createDepartment(DepartmentDTO departmentDTO) {
        Department department = modelMapper.map(departmentDTO, Department.class);
        return modelMapper.map(departmentRepository.save(department), DepartmentDTO.class);
    }

    public DepartmentDTO updateDepartment(DepartmentDTO departmentDTO, Long departmentId) {
        isExistByDepartmentId(departmentId);
        Department department =departmentRepository.findById(departmentId).get();
        Department newDepartment = modelMapper.map(departmentDTO, Department.class);
        newDepartment.setId(departmentId);
        newDepartment.setCreatedTime(department.getCreatedTime());
        return modelMapper.map(departmentRepository.save(newDepartment), DepartmentDTO.class);
    }

    public Boolean deleteDepartmentById(Long departmentId) {
        isExistByDepartmentId(departmentId);
        departmentRepository.deleteById(departmentId);
        return true;
    }

    private void isExistByDepartmentId(Long departmentId) {
        boolean exists = departmentRepository.existsById(departmentId);
        if (!exists) throw new DepartmentNotFoundException("Department not exist with id: "+ departmentId);
    }
}
