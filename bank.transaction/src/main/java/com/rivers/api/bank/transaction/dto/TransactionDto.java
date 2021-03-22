package com.rivers.api.bank.transaction.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.sql.Timestamp;

@Data
public class TransactionDto implements ResponseDto{

    @NotNull(message = "accountNumber can not be empty!")
    @JsonProperty("accountNumber")
    private String accountNumber;

    @NotNull(message = "transactionTs can not be empty!")
    @JsonProperty("transactionTs")
    private Timestamp transactionTs;

    @NotNull(message = "type can not be empty!")
    @JsonProperty("type")
    private String type;

    @NotNull(message = "amount can not be empty!")
    @JsonProperty("amount")
    private BigDecimal amount;
}

