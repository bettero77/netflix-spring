package com.practicetask.netflixandspringbaby.controller;

import com.practicetask.netflixandspringbaby.domain.Employee;
import com.practicetask.netflixandspringbaby.dto.EmployeeWithWorkspace;
import com.practicetask.netflixandspringbaby.feign.WorkspaceResource;
import com.practicetask.netflixandspringbaby.service.EmployeeService;
import feign.Feign;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/employees")
//@RibbonClient(name = "workspaces-api")
class EmployeeAPIController {

//    @LoadBalanced
//    @Bean
//    RestTemplate restTemplate() {
//        return new RestTemplate();
//    }
//
//    @Autowired
//    RestTemplate restTemplate;

    @Autowired
    EmployeeService employeeService;

    @Autowired
    WorkspaceResource workspaceAPIClient;

    @RequestMapping("/{id}")
    public EmployeeWithWorkspace getWorkspaceById(@PathVariable("id") String id) {
        Employee employee = employeeService.findEmployee(id);
        return new EmployeeWithWorkspace(
                employee.getId(),
                employee.getFirstName(),
                employee.getLastName(),
                employee.getEmail(),
                workspaceAPIClient.getWorkspaceById(String.valueOf(employee.getWorkspaceId()))
        );
    }

//    def describeEmployee(@PathVariable("id") String id) {
//        def employee = employeeService.findEmployee(id)
//
//                [
//                id       : employee.id,
//                firstName: employee.firstName,
//                lastName : employee.lastName,
//                email    : employee.email,
//                workspace: workspaceAPIClient.getWorkspaceById(employee.workspaceId)
////                workspace: this.restTemplate.getForObject("http://workspaces-api/workspaces/{id}", Workspace.class, employee.workspaceId)
////                workspace: this.restTemplate.getForObject("http://localhost:9090/workspaces/{id}", Workspace.class, employee.workspaceId)
////                workspace: this.restTemplate.getForObject("http://localhost:8081/workspaces/{id}", Workspace.class, employee.workspaceId)
//        ]
//    }
}