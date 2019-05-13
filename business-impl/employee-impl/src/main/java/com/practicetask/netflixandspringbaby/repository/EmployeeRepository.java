package com.practicetask.netflixandspringbaby.repository;

import com.practicetask.netflixandspringbaby.domain.Employee;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface EmployeeRepository extends CrudRepository<Employee, Integer> {

    List<Employee> findByLastName(String lastName);
}
