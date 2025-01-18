package com.tanvir.dems.config;

import com.tanvir.dems.dto.EmployeeDto;
import com.tanvir.dems.entity.Employee;
import org.modelmapper.Conditions;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {
    @Bean
    public ModelMapper modelMapper() {
        ModelMapper mapper = new ModelMapper();
        mapper.getConfiguration().setPropertyCondition(Conditions.isNotNull());
        mapper.typeMap(EmployeeDto.class, Employee.class).addMappings(mapping -> {
            mapping.skip(Employee::setEmployeeId);
        });
        return mapper;
    }
}