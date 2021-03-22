package com.rivers.api.bank.transaction.entity;

import com.rivers.api.bank.transaction.dto.AccountBalanceDto;
import com.rivers.api.bank.transaction.dto.AccountDto;
import lombok.Data;
import org.springframework.beans.BeanUtils;

import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Timestamp;

@Data
@Table(name = "account_trx")
@Entity
public class AccountTrxEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "account_number")
    private String accountNumber;

    @Column(name = "balance")
    private BigDecimal balance;

    @Column(name = "last_update_timestamp")
    private Timestamp lastUpdateTimestamp;

    @Column(name = "trx_type")
    private String trxType;

    public AccountDto toDto(){
        AccountDto accountDto = new AccountDto();
        BeanUtils.copyProperties(this ,accountDto );
        return accountDto;
    }

    public AccountBalanceDto toAccountBalanceDto(){
        AccountBalanceDto accountBalanceDto = new AccountBalanceDto();
        BeanUtils.copyProperties(this ,accountBalanceDto );
        return accountBalanceDto;
    }
}
