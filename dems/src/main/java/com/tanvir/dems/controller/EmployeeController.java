package com.tanvir.dems.controller;

import com.tanvir.dems.dto.EmployeeDto;
import com.tanvir.dems.dto.EmployeeRankDto;
import com.tanvir.dems.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RequestMapping("/employees")
@RestController
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public EmployeeDto createEmployee(@RequestBody EmployeeDto employeeDto) {
        return employeeService.createEmployee(employeeDto);
    }

    @GetMapping("/{employeeId}")
    @ResponseStatus(HttpStatus.OK)
    public EmployeeDto getEmployee(@PathVariable Long employeeId) {
        return employeeService.getEmployee(employeeId);
    }

    @PutMapping("/{employeeId}")
    @ResponseStatus(HttpStatus.OK)
    public EmployeeDto updateEmployee(@PathVariable Long employeeId, @RequestBody EmployeeDto employeeDto) {
        return employeeService.updateEmployee(employeeId, employeeDto);
    }

    @DeleteMapping("/{employeeId}")
    @ResponseStatus(HttpStatus.OK)
    public EmployeeDto deleteEmployee(@PathVariable Long employeeId) {
       return employeeService.deleteEmployee(employeeId);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<EmployeeDto> getAllEmployees() {
        return employeeService.getAllEmployees();
    }

    @GetMapping("/search")
    @ResponseStatus(HttpStatus.OK)
    public List<EmployeeDto> searchEmployees(@RequestParam(required = false) String name,
                                             @RequestParam(required = false) String department,
                                             @RequestParam(required = false) BigDecimal minSalary,
                                             @RequestParam(required = false) BigDecimal maxSalary) {
        return employeeService.searchEmployees(name, department, minSalary, maxSalary);
    }

    @GetMapping("/ranks/{departmentId}")
    @ResponseStatus(HttpStatus.OK)
    public List<EmployeeRankDto> getEmployeeRanksByDepartment(@PathVariable Long departmentId) {
        return employeeService.getEmployeeRanksByDepartment(departmentId);
    }
}