package com.example.mapper;


import com.example.entity.User;
import com.example.model.dto.UserRequestDto;
import com.example.model.dto.UserResponseDto;

import java.util.List;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserMapper {

    private final AccountMapper accountMapper;

    public UserResponseDto mapEntityToDto(User user) {
        return UserResponseDto.builder()
                              .email(user.getEmail())
                              .username(user.getUsername())
                              .accounts(accountMapper.mapEntitiesToDtos(user.getAccounts()))
                              .build();
    }

    public List<UserResponseDto> mapEntitiesToDtos(List<User> users) {
        return users.stream()
                    .map(this::mapEntityToDto)
                    .toList();
    }

    public User mapDtoToEntity(UserRequestDto dto) {
        return User.builder()
                   .username(dto.getUsername())
                   .email(dto.getEmail())
                   .firstname(dto.getFirstname())
                   .lastname(dto.getLastname())
                   .email(dto.getEmail())
                   .birthDate(dto.getBirthDate())
                   .role(dto.getRole())
                   .build();
    }
}