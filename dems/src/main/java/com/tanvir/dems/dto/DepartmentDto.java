package com.tanvir.dems.dto;

import lombok.Data;

@Data
public class DepartmentDto {
    private Long departmentId;
    private String name;
    private Long userId;
    private Long employeeId;
}
