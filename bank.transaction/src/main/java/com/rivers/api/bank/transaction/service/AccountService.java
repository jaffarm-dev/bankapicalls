package com.rivers.api.bank.transaction.service;

import com.rivers.api.bank.transaction.dto.AccountDto;
import com.rivers.api.bank.transaction.dto.ResponseDto;
import com.rivers.api.bank.transaction.dto.TransactionDto;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Map;

public interface AccountService {

    ResponseEntity<ResponseDto> create(AccountDto accountDto);

    ResponseEntity<ResponseDto> postTransaction(TransactionDto transactionDto);

    ResponseEntity<ResponseDto> getLatestBalance(String accountNumber);

    ResponseEntity<List<ResponseDto>> getTransactions(Map<String , String> headers, String accountNumber);
}
