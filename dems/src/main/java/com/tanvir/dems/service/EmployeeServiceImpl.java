package com.tanvir.dems.service;

import com.tanvir.dems.dto.EmployeeDto;
import com.tanvir.dems.entity.Department;
import com.tanvir.dems.entity.Employee;
import com.tanvir.dems.entity.User;
import com.tanvir.dems.exceptions.ResourceNotFoundException;
import com.tanvir.dems.repository.DepartmentRepository;
import com.tanvir.dems.repository.EmployeeRepository;
import com.tanvir.dems.repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private DepartmentRepository departmentRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ModelMapper modelMapper;

    public EmployeeDto createEmployee(EmployeeDto employeeDto) {
        Department department = departmentRepository.findById(employeeDto.getDepartmentId())
                .orElseThrow(() -> new ResourceNotFoundException("Department", "departmentId", employeeDto.getDepartmentId()));

        Employee employee = modelMapper.map(employeeDto, Employee.class);
        employee.setDepartment(department);
        Employee savedEmployee = employeeRepository.save(employee);

        return modelMapper.map(savedEmployee, EmployeeDto.class);
    }

    public EmployeeDto getEmployee(Long employeeId) {
        Employee employee = employeeRepository.findById(employeeId)
                .orElseThrow(() -> new ResourceNotFoundException("Employee", "employeeId", employeeId));
        return modelMapper.map(employee, EmployeeDto.class);
    }

    public EmployeeDto updateEmployee(Long employeeId, EmployeeDto employeeDto) {
        Employee employee = employeeRepository.findById(employeeId)
                .orElseThrow(() -> new ResourceNotFoundException("Employee", "employeeId", employeeId));

        Department department = departmentRepository.findById(employeeDto.getDepartmentId())
                .orElseThrow(() -> new ResourceNotFoundException("Department", "departmentId", employeeDto.getDepartmentId()));

        modelMapper.map(employeeDto, employee);
        employee.setDepartment(department);
        Employee updatedEmployee = employeeRepository.save(employee);

        return modelMapper.map(updatedEmployee, EmployeeDto.class);
    }

    public void deleteEmployee(Long employeeId) {
        Employee employee = employeeRepository.findById(employeeId)
                .orElseThrow(() -> new ResourceNotFoundException("Employee", "employeeId", employeeId));
        employeeRepository.delete(employee);
    }

    public EmployeeDto assignUser(Long employeeId, Long userId) {
        Employee employee = employeeRepository.findById(employeeId)
                .orElseThrow(() -> new ResourceNotFoundException("Employee", "employeeId", employeeId));
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User", "userId", userId));
        employee.setUser(user);
        Employee updatedEmployee = employeeRepository.save(employee);
        return modelMapper.map(updatedEmployee, EmployeeDto.class);
    }

    public List<EmployeeDto> getAllEmployees() {
        List<Employee> employees = employeeRepository.findAll();
        return employees.stream()
                .map(employee -> modelMapper.map(employee, EmployeeDto.class))
                .toList();
    }

    public List<EmployeeDto> searchEmployees(String name, String department, BigDecimal minSalary, BigDecimal maxSalary) {
        List<Employee> employees = employeeRepository.findEmployeesWithFilters(name, department, minSalary, maxSalary);
        return employees.stream()
                .map(employee -> modelMapper.map(employee, EmployeeDto.class))
                .toList();
    }


}