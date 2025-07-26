package com.learn.spring.boot.web.springbootweb.service;

import com.learn.spring.boot.web.springbootweb.dto.EmployeeDTO;
import com.learn.spring.boot.web.springbootweb.entities.Employee;
import com.learn.spring.boot.web.springbootweb.exceptions.ResourceNotFoundException;
import com.learn.spring.boot.web.springbootweb.repositories.EmployeeRepository;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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

    /*public List<EmployeeDTO> getAllEmployees() {
        List<Employee> employees = employeeRepository.findAll();
        return employees.stream()
                .map(employee -> modelMapper.map(employee, EmployeeDTO.class))
                .collect(Collectors.toList());
    }*/

    public Page<EmployeeDTO> getAllEmployeesWithPageable(Integer age, Pageable pageable) {
        Page<Employee> employees = (age == null)
                ? employeeRepository.findAll(pageable)
                : employeeRepository.findByAge(age, pageable);

        return employees.map(emp -> modelMapper.map(emp, EmployeeDTO.class));
    }

    public List<EmployeeDTO> getAllEmployees(Integer age, Pageable pageable) {
        Page<Employee> page = (age == null)
                ? employeeRepository.findAll(pageable)
                : employeeRepository.findByAge(age, pageable);

        return page.stream()
                .map(emp -> modelMapper.map(emp, EmployeeDTO.class))
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
        isExistByEmployeeId(employeeId);
        Employee employee = employeeRepository.findById(employeeId).get();
        Employee newEmployee = modelMapper.map(employeeDTO, Employee.class);
        newEmployee.setId(employeeId);
        newEmployee.setCreatedAt(employee.getCreatedAt());

        return modelMapper.map(employeeRepository.save(newEmployee), EmployeeDTO.class);

    }

    public Boolean deleteEmployeeById(Long employeeId) {
        isExistByEmployeeId(employeeId);
        employeeRepository.deleteById(employeeId);
        return true;
    }

    public EmployeeDTO updatePartialEmployeeById(Map<String, Object> updates, Long employeeId) {
        isExistByEmployeeId(employeeId);

        Employee existingEmployee = employeeRepository.findById(employeeId).get();
        updates.forEach((field, value) ->{
            Field fieldToBeUpdated = ReflectionUtils.findRequiredField(Employee.class, field);
            fieldToBeUpdated.setAccessible(true);
            ReflectionUtils.setField(fieldToBeUpdated, existingEmployee, value);
        });

        return modelMapper.map(employeeRepository.save(existingEmployee), EmployeeDTO.class);
    }

    private void isExistByEmployeeId(Long employeeId) {
        boolean exists = employeeRepository.existsById(employeeId);
        if (!exists) throw new ResourceNotFoundException("Employee not exist with id: "+ employeeId);
    }
}
