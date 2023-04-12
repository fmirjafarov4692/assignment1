package com.example.model.dto;

import com.example.model.enums.Currency;
import com.example.model.enums.Status;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Builder
public class AccountRequestDto {
    private Integer userId;
    private BigDecimal number;
    private BigDecimal balance;
    private Currency currency;
    private Status status;
}
