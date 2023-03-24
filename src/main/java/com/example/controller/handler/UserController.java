package com.example.controller;

import com.example.model.dto.SortRequest;
import com.example.model.dto.UserRequestDto;
import com.example.model.dto.UserResponseDto;
import com.example.model.pagination.PageDto;
import com.example.service.UserService;
import java.util.List;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping
    public void save(@RequestBody UserRequestDto user) {
         userService.addUser(user);
    }


    @GetMapping
    public List<UserResponseDto> getAll() {
        return userService.findAll();
    }

    @GetMapping("/{id}")
    public UserResponseDto getById(@PathVariable Integer id) {
        return userService.getById(id);
    }

    @GetMapping("/find-by-email")
    public UserResponseDto getByEmail(@RequestParam String email) {
        return userService.getByEmail(email);
    }

    @GetMapping("/pagination")
    public PageDto pagination(@RequestParam Integer size, @RequestParam Integer page){
        return userService.pagination(page,size);
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable Integer id) {
        userService.deleteUser(id);
    }

    @GetMapping("/sorting")
    public List<UserResponseDto> sorted(@RequestParam String fieldName, @RequestParam String sortType){
        return userService.sort(fieldName, sortType);
    }
    @PutMapping("/{id}")
    public void update(@PathVariable Integer id, @RequestBody UserRequestDto user) {
        userService.updateUser(id, user);
    }
}