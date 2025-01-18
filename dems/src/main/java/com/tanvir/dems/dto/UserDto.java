package com.tanvir.dems.dto;

import com.tanvir.dems.entity.UserRole;
import lombok.Data;

@Data
public class UserDto {
    private Long userId;
    private String username;
    private String password;
    private Long employeeId;
    private UserRole role;
}
