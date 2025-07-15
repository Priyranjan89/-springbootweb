package com.learn.spring.boot.web.springbootweb.repositories;

import com.learn.spring.boot.web.springbootweb.entities.Department;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DepartmentRepository extends JpaRepository<Department, Long> {
}
