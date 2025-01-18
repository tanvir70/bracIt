package com.tanvir.dems.service;

import com.tanvir.dems.dto.EmployeeDto;
import com.tanvir.dems.dto.EmployeeRankDto;

import java.math.BigDecimal;
import java.util.List;

public interface EmployeeService {

    EmployeeDto createEmployee(EmployeeDto employeeDto);

    EmployeeDto getEmployee(Long employeeId);

    EmployeeDto updateEmployee(Long employeeId, EmployeeDto employeeDto);

    EmployeeDto deleteEmployee(Long employeeId);

    List<EmployeeDto> getAllEmployees();

    List<EmployeeDto> searchEmployees(String name, String department, BigDecimal minSalary, BigDecimal maxSalary);

    List<EmployeeRankDto> getEmployeeRanksByDepartment(Long departmentId);
}
