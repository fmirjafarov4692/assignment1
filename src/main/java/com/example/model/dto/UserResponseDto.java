package com.example.model.dto;

import java.util.List;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserResponseDto {
    private String username;
    private String email;
    private List<AccountResponseDto> accounts;
}