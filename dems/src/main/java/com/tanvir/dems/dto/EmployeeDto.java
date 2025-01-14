package com.tanvir.dems.dto;

import com.tanvir.dems.entity.Department;
import lombok.Data;

import java.time.LocalDate;

@Data
public class EmployeeDto {
    public Long employeeId;
    public String name;
    public String designation;
    public Long departmentId;
    public String departmentName;
    public LocalDate joiningDate;
    public String salary;
    public String attributes;
    public Long userId;



}
