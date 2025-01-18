package com.tanvir.dems.dto;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Map;

@Data
public class EmployeeDto {
    public Long employeeId;
    public String name;
    public String designation;
    public Long departmentId;
    public String departmentName;
    public LocalDate joiningDate;
    public BigDecimal salary;
    private Map<String, Object> additionalAttributes;
    public Long userId;
}
