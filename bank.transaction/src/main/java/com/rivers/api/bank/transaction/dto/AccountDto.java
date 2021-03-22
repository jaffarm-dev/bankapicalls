package com.rivers.api.bank.transaction.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.rivers.api.bank.transaction.entity.AccountTrxEntity;
import lombok.Data;
import org.springframework.beans.BeanUtils;

import java.math.BigDecimal;
import java.sql.Timestamp;

@Data
public class AccountDto implements ResponseDto{

    @JsonProperty("id")
    private int id;

    @JsonProperty("accountNumber")
    private String accountNumber;

    @JsonProperty("balance")
    private BigDecimal balance;

    @JsonProperty("lastUpdateTimestamp")
    private Timestamp lastUpdateTimestamp;

    @JsonProperty("trx_type")
    private String trxType;

    public AccountTrxEntity toEntity(){
        AccountTrxEntity accountTrxEntity = new AccountTrxEntity();
        BeanUtils.copyProperties(this , accountTrxEntity);
        return accountTrxEntity;
    }
}
