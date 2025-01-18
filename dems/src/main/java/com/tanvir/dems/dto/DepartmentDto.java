package com.tanvir.dems.dto;

import lombok.Data;

import java.util.List;

@Data
public class DepartmentDto {
    private Long departmentId;
    private String name;
    private List<EmployeeDto> employees;
}
