package com.practicetask.netflixandspringbaby.dto;

import com.practicetask.netflixandspringbaby.domain.Workspace;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@AllArgsConstructor
@Accessors(chain = true)
public class EmployeeWithWorkspace {
    private Integer id;
    private String firstName;
    private String lastName;
    private String email;
    private Workspace workspace;
}
