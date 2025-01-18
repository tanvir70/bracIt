package com.tanvir.dems.service;

import com.tanvir.dems.dto.UserDto;

import java.util.List;

public interface UserService {
    UserDto createUser(UserDto userDto);
    List<UserDto> getAllUsers();
}
