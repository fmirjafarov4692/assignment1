package com.example.mapper;

import com.example.entity.Account;
import com.example.model.dto.AccountRequestDto;
import com.example.model.dto.AccountResponseDto;

import java.util.List;

import org.springframework.stereotype.Component;

@Component
public class AccountMapper {

    public AccountResponseDto mapEntityToDto(Account account) {
        return AccountResponseDto.builder()
                                 .balance(account.getBalance())
                                 .number(account.getNumber())
                                 .build();
    }

    public List<AccountResponseDto> mapEntitiesToDtos(List<Account> accounts) {
        return accounts.stream()
                       .map(this::mapEntityToDto)
                       .toList();
    }

    public Account mapDtoToEntity(AccountRequestDto dto) {
        return Account.builder()
                      .balance(dto.getBalance())
                      .currency(dto.getCurrency())
                      .number(dto.getNumber())
                      .status(dto.getStatus())
                      .build();
    }
}