package com.rivers.api.bank.transaction.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class AccountBalanceDto implements ResponseDto{

    @JsonProperty("accountNumber")
    private String accountNumber;

    @JsonProperty("balance")
    private BigDecimal balance;
}
