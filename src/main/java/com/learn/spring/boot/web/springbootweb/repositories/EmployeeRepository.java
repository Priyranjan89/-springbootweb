package com.learn.spring.boot.web.springbootweb.repositories;

import com.learn.spring.boot.web.springbootweb.entities.Employee;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    List<Employee> findByOrderById();
    List<Employee> findByOrderBySalary();

    Page<Employee> findByAge(Integer age, Pageable pageable);

}
