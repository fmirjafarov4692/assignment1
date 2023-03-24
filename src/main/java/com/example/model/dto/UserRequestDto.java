package com.example.model.dto;

import com.example.model.enums.Role;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
@Builder
public class UserRequestDto {
    private String username;
    private String email;
    private String firstname;
    private String lastname;
    private LocalDate birthDate;
    private Role role;
}
