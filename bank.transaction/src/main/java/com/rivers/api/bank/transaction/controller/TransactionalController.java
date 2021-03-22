package com.rivers.api.bank.transaction.controller;

import com.rivers.api.bank.transaction.dto.AccountDto;
import com.rivers.api.bank.transaction.dto.ResponseDto;
import com.rivers.api.bank.transaction.dto.TransactionDto;
import com.rivers.api.bank.transaction.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/account")
public class TransactionalController {

    private final AccountService accountService;

    @Autowired
    public TransactionalController(AccountService accountService) {
        this.accountService = accountService;
    }

    @PostMapping("/update")
    public ResponseEntity<ResponseDto> createAccount(@Valid @RequestBody AccountDto accountDto){
        return accountService.create(accountDto);
    }

    @PostMapping("/transaction")
    public ResponseEntity<ResponseDto> postTransaction(@Valid @RequestBody TransactionDto transactionDto){
        return accountService.postTransaction(transactionDto);
    }

    @GetMapping("/balance/{accountNumber}")
    public ResponseEntity<ResponseDto> getLatestBalance(@PathVariable(name = "accountNumber") String accountNumber){
        return accountService.getLatestBalance(accountNumber);
    }

    @GetMapping("/transaction/{accountNumber}")
    public ResponseEntity<List<ResponseDto>> getTransactions(@RequestHeader Map<String , String> headers,
                                                @PathVariable(name = "accountNumber") String accountNumber){

        return accountService.getTransactions(headers , accountNumber);
    }

}


