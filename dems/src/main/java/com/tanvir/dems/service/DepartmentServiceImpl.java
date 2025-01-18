package com.tanvir.dems.service;

import com.tanvir.dems.dto.DepartmentDto;
import com.tanvir.dems.entity.Department;
import com.tanvir.dems.exceptions.ApiException;
import com.tanvir.dems.exceptions.ResourceNotFoundException;
import com.tanvir.dems.repository.DepartmentRepository;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
public class DepartmentServiceImpl implements DepartmentService {

    @Autowired
    private DepartmentRepository departmentRepository;

    @Autowired
    private ModelMapper modelMapper;


    @Override
    public DepartmentDto createDepartment(DepartmentDto departmentDto) {
        log.info("Creating new department with name: {}", departmentDto.getName());

        if (departmentRepository.findByName(departmentDto.getName()).isPresent()) {
            throw new ApiException("Department with name: " + departmentDto.getName() + " already exists");
        }

        Department department = modelMapper.map(departmentDto, Department.class);
        Department savedDepartment = departmentRepository.save(department);
        return modelMapper.map(savedDepartment, DepartmentDto.class);

    }

    @Override
    public List<DepartmentDto> getAllDepartments() {
        log.info("Fetching all departments");
        List<Department> departments = departmentRepository.findAll();
        return departments.stream()
                .map(department -> modelMapper.map(department, DepartmentDto.class))
                .collect(Collectors.toList());
    }
}
