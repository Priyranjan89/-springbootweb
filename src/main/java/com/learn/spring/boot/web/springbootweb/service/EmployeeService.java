package com.learn.spring.boot.web.springbootweb.service;

import com.learn.spring.boot.web.springbootweb.dto.EmployeeDTO;
import com.learn.spring.boot.web.springbootweb.entities.Employee;
import com.learn.spring.boot.web.springbootweb.repositories.EmployeeRepository;
import org.modelmapper.ModelMapper;
import org.springframework.data.util.ReflectionUtils;
import org.springframework.stereotype.Service;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class EmployeeService {

    private final EmployeeRepository employeeRepository;
    private final ModelMapper modelMapper;

    public EmployeeService(EmployeeRepository employeeRepository, ModelMapper modelMapper) {
        this.employeeRepository = employeeRepository;
        this.modelMapper = modelMapper;
    }

    public Optional<EmployeeDTO> getEmployeeById(Long employeeId) {
        return employeeRepository.findById(employeeId)
                .map(employee1 -> modelMapper.map(employee1, EmployeeDTO.class));
    }

    public List<EmployeeDTO> getAllEmployees() {
        List<Employee> employees = employeeRepository.findAll();
        return employees.stream()
                .map(employee -> modelMapper.map(employee, EmployeeDTO.class))
                .collect(Collectors.toList());
    }

    public EmployeeDTO createEmployee(EmployeeDTO employeeDTO) {
        Employee employee = modelMapper.map(employeeDTO, Employee.class);
        return modelMapper.map(employeeRepository.save(employee), EmployeeDTO.class);
    }

    public List<EmployeeDTO> createAllEmployees(List<EmployeeDTO> employeeDTOs) {
        List<Employee> employees = employeeDTOs.stream()
                .map(employeeDTO -> modelMapper.map(employeeDTO, Employee.class))
                .collect(Collectors.toList());

        List<Employee> saveEmployees = employeeRepository.saveAll(employees);

        return saveEmployees.stream()
                .map(employee -> modelMapper.map(employee, EmployeeDTO.class))
                .collect(Collectors.toList());
    }

    public EmployeeDTO updateEmployeeById(EmployeeDTO employeeDTO, Long employeeId) {
        Employee newEmployee = modelMapper.map(employeeDTO, Employee.class);
        newEmployee.setId(employeeId);
        return modelMapper.map(employeeRepository.save(newEmployee), EmployeeDTO.class);

    }

    public Boolean deleteEmployeeById(Long employeeId) {
        boolean exists = isExistByEmployeeId(employeeId);
        if (!exists) return false;

        employeeRepository.deleteById(employeeId);
        return true;
    }

    public EmployeeDTO updatePartialEmployeeById(Map<String, Object> updates, Long employeeId) {

        boolean exists = isExistByEmployeeId(employeeId);
        if (!exists) return null;

        Employee existingEmployee = employeeRepository.findById(employeeId).get();
        updates.forEach((field, value) ->{
            Field fieldToBeUpdated = ReflectionUtils.findRequiredField(Employee.class, field);
            fieldToBeUpdated.setAccessible(true);
            ReflectionUtils.setField(fieldToBeUpdated, existingEmployee, value);
        });
        return modelMapper.map(employeeRepository.save(existingEmployee), EmployeeDTO.class);
    }

    private boolean isExistByEmployeeId(Long employeeId) {
        boolean exists = employeeRepository.existsById(employeeId);
        return exists;
    }
}
