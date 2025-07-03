package com.learn.spring.boot.web.springbootweb.repositories;

import com.learn.spring.boot.web.springbootweb.entities.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {
}
