package com.example.service;

import com.example.entity.User;
import com.example.mapper.UserMapper;
import com.example.model.dto.SortRequest;
import com.example.model.dto.UserRequestDto;
import com.example.model.dto.UserResponseDto;
import com.example.model.exception.NotFoundException;
import com.example.model.pagination.PageDto;
import com.example.repository.UserRepository;

import java.util.List;
import java.util.stream.Collectors;

import com.example.model.constants.ExceptionConstants;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    public void addUser(UserRequestDto dto) {
        var entity = userMapper.mapDtoToEntity(dto);
        userRepository.save(entity);
    }

    public List<UserResponseDto> findAll( ) {
        return userMapper.mapEntitiesToDtos(userRepository.findAll());

    }

    public UserResponseDto getById(Integer id) {
        var user = userRepository.findById(id).orElseThrow(
                ( ) -> new NotFoundException(String.format(ExceptionConstants.NOT_FOUND_MESSAGE, "id " + id),
                                             ExceptionConstants.NOT_FOUND_CODE));
        return userMapper.mapEntityToDto(user);
    }

    public UserResponseDto getByEmail(String email) {
        User user = userRepository.findByEmailContaining(email)
                                  .orElseThrow(( ) -> new NotFoundException(
                                          String.format(ExceptionConstants.NOT_FOUND_MESSAGE, "email" + email),
                                          ExceptionConstants.NOT_FOUND_CODE));
        return userMapper.mapEntityToDto(user);
    }

    public void updateUser(Integer id, UserRequestDto userDto) {
        User user = userRepository.findById(id).orElseThrow(
                ( ) -> new NotFoundException(String.format(ExceptionConstants.NOT_FOUND_MESSAGE, "id " + id),
                                             ExceptionConstants.NOT_FOUND_CODE));
        user.setUsername(userDto.getUsername());
        user.setEmail(userDto.getEmail());
        user.setFirstname(userDto.getFirstname());
        user.setLastname(userDto.getLastname());
        user.setBirthDate(userDto.getBirthDate());
        user.setRole(userDto.getRole());
        userRepository.save(user);
    }


    public PageDto pagination(Integer page, Integer size) {
        var user = userRepository.findAll(PageRequest.of(page, size));
        return PageDto.builder()
                               .dtos(user.getContent().stream().map(userMapper::mapEntityToDto).collect(Collectors.toList()))
                               .lastPage(user.getTotalPages())
                               .hasNext(user.hasNext())
                               .build();
    }

    public void deleteUser(Integer id) {
        var entity = userRepository.findById(id).orElseThrow(
                ( ) -> new NotFoundException(String.format(ExceptionConstants.NOT_FOUND_MESSAGE, "id " + id),
                                             ExceptionConstants.NOT_FOUND_CODE));
        userRepository.delete(entity);
    }

    public List<UserResponseDto> sort(String fieldName, String sortType) {
        List<User> entity;
        if (sortType.equalsIgnoreCase("DESC")) entity = userRepository.findAll(
                Sort.by(Sort.Direction.DESC, fieldName));
        else entity = userRepository.findAll(Sort.by(Sort.Direction.ASC, fieldName));
        return entity.stream().map(userMapper::mapEntityToDto).collect(Collectors.toList());
    }
}