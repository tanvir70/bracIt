package com.tanvir.dems.service;

import com.tanvir.dems.dto.EmployeeDto;
import com.tanvir.dems.dto.EmployeeRankDto;
import com.tanvir.dems.entity.Department;
import com.tanvir.dems.entity.Employee;
import com.tanvir.dems.entity.User;
import com.tanvir.dems.exceptions.ApiException;
import com.tanvir.dems.exceptions.ResourceNotFoundException;
import com.tanvir.dems.repository.DepartmentRepository;
import com.tanvir.dems.repository.EmployeeRepository;
import com.tanvir.dems.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository employeeRepository;
    private final DepartmentRepository departmentRepository;
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;

    @Override
    @Transactional
    @CachePut(value = "employee", key = "#result.employeeId")
    public EmployeeDto createEmployee(EmployeeDto employeeDto) {
        log.info("Creating new employee with name: {}", employeeDto.getName());

        // Validate department
        Department department = departmentRepository.findById(employeeDto.getDepartmentId())
                .orElseThrow(() -> new ResourceNotFoundException("Department", "departmentId", employeeDto.getDepartmentId()));

        // Validate user if provided
        if (employeeDto.getUserId() != null) {
            User user = userRepository.findById(employeeDto.getUserId())
                    .orElseThrow(() -> new ResourceNotFoundException("User", "userId", employeeDto.getUserId()));
            if (employeeRepository.existsByUser_UserId(employeeDto.getUserId())) {
                throw new ApiException("User is already assigned to another employee");
            }
        }

        try {
            Employee employee = modelMapper.map(employeeDto, Employee.class);
            employee.setDepartment(department);

            if (employeeDto.getUserId() != null) {
                User user = userRepository.findById(employeeDto.getUserId())
                        .orElseThrow(() -> new ResourceNotFoundException("Department", "departmentId", employeeDto.getDepartmentId()));
                employee.setUser(user);
            }

            Employee savedEmployee = employeeRepository.save(employee);
            EmployeeDto responseDto = modelMapper.map(savedEmployee, EmployeeDto.class);
            responseDto.setDepartmentName(department.getName());

            log.info("Successfully created employee with ID: {}", savedEmployee.getEmployeeId());
            return responseDto;

        } catch (Exception e) {
            log.error("Error creating employee: {}", e.getMessage());
            throw new ApiException("Error creating employee: " + e.getMessage());
        }
    }

    @Override
    @Cacheable(value = "employee", key = "#employeeId", unless = "#result == null")
    public EmployeeDto getEmployee(Long employeeId) {
        log.info("Fetching employee with ID: {}", employeeId);
        Employee employee = employeeRepository.findById(employeeId)
                .orElseThrow(() -> new ResourceNotFoundException("Employee", "employeeId", employeeId));

        EmployeeDto employeeDto = modelMapper.map(employee, EmployeeDto.class);
        employeeDto.setDepartmentName(employee.getDepartment().getName());
        return employeeDto;
    }

    @Override
    @Transactional
    @CachePut(value = "employee", key = "#employeeId")
    public EmployeeDto updateEmployee(Long employeeId, EmployeeDto employeeDto) {
        log.info("Updating employee with ID: {}", employeeId);

        Employee employee = employeeRepository.findById(employeeId)
                .orElseThrow(() -> new ResourceNotFoundException("Employee", "employeeId", employeeId));

        Department department = departmentRepository.findById(employeeDto.getDepartmentId())
                .orElseThrow(() -> new ResourceNotFoundException("Department", "departmentId", employeeDto.getDepartmentId()));

        if (employeeDto.getName() != null) {
            employee.setName(employeeDto.getName());
        }
        if (employeeDto.getDesignation() != null) {
            employee.setDesignation(employeeDto.getDesignation());
        }
        if (employeeDto.getJoiningDate() != null) {
            employee.setJoiningDate(employeeDto.getJoiningDate());
        }
        if (employeeDto.getSalary() != null) {
            employee.setSalary(employeeDto.getSalary());
        }
        if (employeeDto.getAdditionalAttributes() != null) {
            employee.setAdditionalAttributes(employeeDto.getAdditionalAttributes());
        }

        // validating user id as create
        if (employeeDto.getUserId() != null) {
            User user = userRepository.findById(employeeDto.getUserId())
                    .orElseThrow(() -> new ResourceNotFoundException("User", "userId", employeeDto.getUserId()));

            if (!employeeDto.getUserId().equals(employee.getUser() != null ? employee.getUser().getUserId() : null) &&
                    employeeRepository.existsByUser_UserId(employeeDto.getUserId())) {
                throw new ApiException("User is already assigned to another employee");
            }
            employee.setUser(user);
        }

        employee.setDepartment(department);

        try {
            Employee updatedEmployee = employeeRepository.save(employee);
            EmployeeDto responseDto = modelMapper.map(updatedEmployee, EmployeeDto.class);
            responseDto.setDepartmentName(department.getName());

            log.info("Successfully updated employee with ID: {}", employeeId);
            return responseDto;
        } catch (Exception e) {
            log.error("Error updating employee: {}", e.getMessage());
            throw new ApiException("Error updating employee: " + e.getMessage());
        }
    }

    @Override
    @Transactional
    @CacheEvict(value = "employee", key = "#employeeId")
    public EmployeeDto deleteEmployee(Long employeeId) {
        log.info("Deleting employee with ID: {}", employeeId);
        Employee employee = employeeRepository.findById(employeeId)
                .orElseThrow(() -> new ResourceNotFoundException("Employee", "employeeId", employeeId));

        EmployeeDto employeeDto = modelMapper.map(employee, EmployeeDto.class);
        employeeDto.setDepartmentName(employee.getDepartment().getName());

        employeeRepository.delete(employee);
        log.info("Successfully deleted employee with ID: {}", employeeId);
        return employeeDto;
    }

    @Override
    @Cacheable(value = "employees", key = "all")
    public List<EmployeeDto> getAllEmployees() {
        log.info("Fetching all employees");
        return employeeRepository.findAll().stream()
                .map(employee -> {
                    EmployeeDto dto = modelMapper.map(employee, EmployeeDto.class);
                    dto.setDepartmentName(employee.getDepartment().getName());
                    return dto;
                })
                .toList();
    }

    @Override
    @Cacheable(value = "employeeSearchResult", key = "#name + #department + #minSalary + #maxSalary",
            unless = "#result.isEmpty()")
    public List<EmployeeDto> searchEmployees(String name, String department, BigDecimal minSalary, BigDecimal maxSalary) {
        log.info("Searching employees with filters - name: {}, department: {}, salary range: {} - {}",
                name, department, minSalary, maxSalary);
        return employeeRepository.findEmployeesWithFilters(name, department, minSalary, maxSalary)
                .stream()
                .map(employee -> {
                    EmployeeDto dto = modelMapper.map(employee, EmployeeDto.class);
                    dto.setDepartmentName(employee.getDepartment().getName());
                    return dto;
                })
                .toList();
    }

    @Override
    public List<EmployeeRankDto> getEmployeeRanksByDepartment(Long departmentId) {
        log.info("Fetching Rank on Salary over departments ");
        return employeeRepository.findEmployeeRanksByDepartment(departmentId)
                .stream()
                .map(res -> {
                    EmployeeRankDto dto = new EmployeeRankDto();
                    dto.setEmployeeName((String) res[0]);
                    dto.setDepartmentName((String) res[1]);
                    dto.setSalary((BigDecimal) res[2]);
                    dto.setRank(((Number) res[3]).intValue());
                    return dto;
                })
                .toList();
    }
}