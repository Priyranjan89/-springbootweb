package com.learn.spring.boot.web.springbootweb.service;

import com.learn.spring.boot.web.springbootweb.dto.EmployeeDTO;
import com.learn.spring.boot.web.springbootweb.utils.EmployeeLoader;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class EmployeeService {

    private EmployeeLoader employeeLoader;
    private Map<Long, EmployeeDTO> employeeData;

    public EmployeeService(EmployeeLoader employeeLoader, Map<Long, EmployeeDTO> employeeData) {
        this.employeeLoader = employeeLoader;
        this.employeeData = employeeLoader.loadEmployees();
    }

    public EmployeeDTO getEmployeeById(Long employeeId) {
        return employeeData.get(employeeId);
    }

    public List<EmployeeDTO> getAllEmployees() {
        return employeeData.values().stream().collect(Collectors.toList());
    }

    public EmployeeDTO createEmployee(EmployeeDTO employee) {
        long id = employeeData.size()+1;
        employee.setId(id);
        employeeData.put(id, employee);
        return employee;
    }
}
