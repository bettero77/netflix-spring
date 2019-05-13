package com.practicetask.netflixandspringbaby.service;

import com.practicetask.netflixandspringbaby.domain.Employee;
import com.practicetask.netflixandspringbaby.exception.NoEmployeeFoundException;
import com.practicetask.netflixandspringbaby.repository.EmployeeRepository;
import org.apache.kafka.common.protocol.types.Field;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Optional;

import static com.google.common.collect.Lists.newArrayList;
import static java.lang.String.format;

@Service
public class EmployeeService {
    public EmployeeService() {
    }

    Logger log = LoggerFactory.getLogger(EmployeeService.class);

    @Resource
    EmployeeRepository employeeRepository;

    private List<Employee> employees = newArrayList(
            new Employee(1, "Ivan", "Ivanov", "Ivan_Ivanov@corpmail.com", 1),
            new Employee(2, "Ivan", "Ivanov", "Ivan_Ivanov@corpmail.com", 2),
            new Employee(3, "Ivan", "Ivanov", "Ivan_Ivanov@corpmail.com", 3),
            new Employee(4, "Ivan", "Ivanov", "Ivan_Ivanov@corpmail.com", 4),
            new Employee(5, "Ivan", "Ivanov", "Ivan_Ivanov@corpmail.com", 5),
            new Employee(6, "Ivan", "Ivanov", "Ivan_Ivanov@corpmail.com", 6),
            new Employee(7, "Ivan", "Ivanov", "Ivan_Ivanov@corpmail.com", 7),
            new Employee(8, "Ivan", "Ivanov", "Ivan_Ivanov@corpmail.com", 8),
            new Employee(9, "Ivan", "Ivanov", "Ivan_Ivanov@corpmail.com", 9),
            new Employee(10, "Ivan", "Ivanov", "Ivan_Ivanov@corpmail.com",10),
            new Employee(11, "Ivan", "Ivanov", "Ivan_Ivanov@corpmail.com",11),
            new Employee(12, "Ivan", "Ivanov", "Ivan_Ivanov@corpmail.com",12),
            new Employee(13, "Ivan", "Ivanov", "Ivan_Ivanov@corpmail.com",13),
            new Employee(14, "Ivan", "Ivanov", "Ivan_Ivanov@corpmail.com",14),
            new Employee(15, "Ivan", "Ivanov", "Ivan_Ivanov@corpmail.com",15)
    );

    public Employee findEmployee(String id) {
        return employees.stream()
                .filter(employee -> employee.getId().equals(Integer.parseInt(id)))
                .findFirst()
                .orElseThrow(() -> new NoEmployeeFoundException(format("No employee found for id: %s", id)));
    }

    public void changeEmployeeWorkspace(String employeeId, String workspaceId) {
        log.info("STARTTTTTTTTTTTTTT " + employeeId + " " + workspaceId);
        Optional<Employee> employee = employeeRepository.findById(Integer.valueOf(employeeId));
        log.info("111111111");
        employee.ifPresent(e -> log.info("curr emp: " + e));
        employee.ifPresent(e -> e.setWorkspaceId(Integer.valueOf(workspaceId)));
        log.info("2222222222");
        employee.ifPresent(e -> log.info("changed emp: " + e));
        employee.ifPresent(e -> employeeRepository.save(e));
        log.info("ENDDDDDDD");
    }

    public void addEmployee(Employee employee) {
        log.info("start saving emp");
        employeeRepository.save(employee);
        log.info("finish saving emp");
    }
}