package com.tanvir.dems.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class EmployeeRankDto {
    private String employeeName;
    private String departmentName;
    private BigDecimal salary;
    private Integer rank;
}
