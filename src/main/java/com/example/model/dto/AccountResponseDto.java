package com.example.model.dto;

import java.math.BigDecimal;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AccountResponseDto {
    private BigDecimal number;
    private BigDecimal balance;
}