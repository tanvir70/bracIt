package com.tanvir.dems.service;

import com.tanvir.dems.dto.UserDto;
import com.tanvir.dems.entity.Employee;
import com.tanvir.dems.entity.User;
import com.tanvir.dems.entity.UserRole;
import com.tanvir.dems.exceptions.ApiException;
import com.tanvir.dems.exceptions.ResourceNotFoundException;
import com.tanvir.dems.repository.EmployeeRepository;
import com.tanvir.dems.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class UserServiceImpl implements UserService {

    @Autowired
    EmployeeRepository employeeRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public UserDto createUser(UserDto userDto) {
        log.info("Creating new User with name: {}", userDto.getUsername());

        if (userRepository.findByUsername(userDto.getUsername()).isPresent()) {
            throw new ApiException("Username Already exists");
        }

        User user = modelMapper.map(userDto, User.class);

        if (user.getRole() == null) {
            user.setRole(UserRole.USER);
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        User savedUser = userRepository.save(user);
        return modelMapper.map(savedUser, UserDto.class);
    }


    @Override
    public List<UserDto> getAllUsers() {
        log.info("Getting all users");
        List<User> users = userRepository.findAll();
        return users.stream()
                .map(user -> modelMapper.map(user, UserDto.class))
                .collect(java.util.stream.Collectors.toList());
    }
}
