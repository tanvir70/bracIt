package com.tanvir.dems.config;

import com.tanvir.dems.dto.EmployeeDto;
import com.tanvir.dems.entity.Employee;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {
    @Bean
    public ModelMapper modelMapper(){
        ModelMapper modelMapper = new ModelMapper();

        modelMapper.createTypeMap(Employee.class, EmployeeDto.class)
                .addMapping(src -> src.getDepartment().getId(), EmployeeDto::setDepartmentId)
                .addMapping(src -> src.getDepartment().getName(), EmployeeDto::setDepartmentName);

        return modelMapper;
    }
}
