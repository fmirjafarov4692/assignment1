package com.example.service;

import com.example.entity.Account;
import com.example.entity.User;
import com.example.mapper.AccountMapper;
import com.example.model.dto.AccountRequestDto;
import com.example.model.dto.AccountResponseDto;
import com.example.model.dto.SortRequest;
import com.example.model.dto.UserResponseDto;
import com.example.model.enums.Currency;
import com.example.model.enums.Status;
import com.example.model.exception.NotFoundException;
import com.example.model.pagination.PageDto;
import com.example.repository.AccountRepository;

import java.util.List;
import java.util.stream.Collectors;

import com.example.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AccountService {

    private final AccountRepository accountRepository;
    private final AccountMapper accountMapper;
    private final UserRepository userRepository;

    public void save(AccountRequestDto account) {
        var entity = accountMapper.mapDtoToEntity(account);
        var userEntity = userRepository.findById(account.getUserId()).orElseThrow(
                ( ) -> new NotFoundException("Account not found", "ACCOUNT_NOT_FOUND")
        );
        entity.setUser(userEntity);
        accountRepository.save(entity);
    }

    public AccountResponseDto getById(Integer id) {
        var entity = accountRepository.findById(id).orElseThrow(( ) -> new RuntimeException("Account not found"));
        return accountMapper.mapEntityToDto(entity);

    }

    public List<AccountResponseDto> getAll( ) {
        List<Account> accounts = accountRepository.findAll();
        return accountMapper.mapEntitiesToDtos(accounts);
    }

    public List<AccountResponseDto> getAllWhereStatusIs(Status status) {
        List<Account> accounts = accountRepository.findAllWhereStatusIs(status);
        return accountMapper.mapEntitiesToDtos(accounts);
    }

    public List<AccountResponseDto> getAllAccountsByCurrencyAndUserId(Currency currency, Integer userId) {
        List<Account> accounts = accountRepository.findAllAccountsByCurrencyAndUserId(currency, userId);
        return accountMapper.mapEntitiesToDtos(accounts);
    }

    public void update(AccountRequestDto dto, Integer id) {
        var entity = accountRepository.findById(id).orElseThrow(( ) -> new RuntimeException("Account not found"));
        entity.setBalance(dto.getBalance());
        entity.setCurrency(dto.getCurrency());
        entity.setNumber(dto.getNumber());
        entity.setStatus(dto.getStatus());
        accountRepository.save(entity);
    }

    public void delete(Integer id) {
        var entity = accountRepository.findById(id).orElseThrow(( ) -> new RuntimeException("Account not found"));
        accountRepository.delete(entity);
    }

    public List<AccountResponseDto> sort(String fieldName, String sortType ) {
        List<Account> entity;
        if (sortType.equalsIgnoreCase("DESC")) entity = accountRepository.findAll(
                Sort.by(Sort.Direction.DESC, fieldName));
        else entity = accountRepository.findAll(Sort.by(Sort.Direction.ASC, fieldName));
        return entity.stream().map(accountMapper::mapEntityToDto).collect(Collectors.toList());
    }

    public PageDto pagination(Integer page, Integer size) {
        var accounts = accountRepository.findAll(PageRequest.of(page, size));
        return PageDto.builder()
                      .dtos(accounts.getContent().stream().map(accountMapper::mapEntityToDto).collect(Collectors.toList()))
                      .lastPage(accounts.getTotalPages())
                      .hasNext(accounts.hasNext())
                      .build();
    }
}