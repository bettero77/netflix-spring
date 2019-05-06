package com.practicetask.netflixandspringbaby.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.experimental.Accessors;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;


@Data
@AllArgsConstructor
@Accessors(chain = true)
@Entity
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private String firstName;
    private String lastName;
    private String email;
    private Integer workspaceId;

    public Employee(String firstName, String lastName, String email, Integer workspaceId) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.workspaceId = workspaceId;
    }
}
