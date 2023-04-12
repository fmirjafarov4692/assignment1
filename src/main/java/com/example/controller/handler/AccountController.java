package com.example.controller;

import com.example.model.dto.AccountRequestDto;
import com.example.model.dto.AccountResponseDto;
import com.example.model.enums.Currency;
import com.example.model.enums.Status;
import com.example.model.pagination.PageDto;
import com.example.service.AccountService;

import java.util.List;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/accounts")
@RequiredArgsConstructor
public class AccountController {
    private final AccountService accountService;

    @PostMapping
    public void addAccount(@RequestBody AccountRequestDto account) {
        accountService.save(account);
    }

    @GetMapping("/{id}")
    public AccountResponseDto getById(@PathVariable Integer id) {
        return accountService.getById(id);
    }

    @GetMapping
    public List<AccountResponseDto> getAll( ) {
        return accountService.getAll();
    }

    @GetMapping("/status")
    public List<AccountResponseDto> getAllWhereStatusIs(@RequestParam Status status) {
        return accountService.getAllWhereStatusIs(status);
    }

    @GetMapping("{userId}/currency")
    public List<AccountResponseDto> getAllAccountsByCurrencyAndUserId(@RequestParam Currency currency,
            @PathVariable Integer userId) {
        return accountService.getAllAccountsByCurrencyAndUserId(currency, userId);
    }

    @PutMapping("/{id}")
    public void update(@RequestBody AccountRequestDto dto, @PathVariable Integer id) {
        accountService.update(dto, id);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer id) {
        accountService.delete(id);
    }

    @GetMapping("/pagination")
    public PageDto pagination(@RequestParam Integer size, @RequestParam Integer page) {
        return accountService.pagination(page, size);
    }

    @GetMapping("/sorting")
    public List<AccountResponseDto> sorted(@RequestParam String fieldName, @RequestParam String sortType) {
        return accountService.sort(fieldName, sortType);
    }

}