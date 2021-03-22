package com.rivers.api.bank.transaction.service.impl;

import com.rivers.api.bank.transaction.constant.TransactionType;
import com.rivers.api.bank.transaction.dto.AccountDto;
import com.rivers.api.bank.transaction.dto.ErrorResponseDto;
import com.rivers.api.bank.transaction.dto.ResponseDto;
import com.rivers.api.bank.transaction.dto.TransactionDto;
import com.rivers.api.bank.transaction.entity.AccountTrxEntity;
import com.rivers.api.bank.transaction.repository.AccountRepository;
import com.rivers.api.bank.transaction.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.*;

@Service
public class AccountServiceImpl implements AccountService {

    private final AccountRepository accountRepository;

    private static final  String DATE_FROM = "datefrom";
    private static final  String DATE_TO = "dateto";
    private static final  String TYPE = "type";

    @Autowired
    public AccountServiceImpl(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @Override
    public ResponseEntity<ResponseDto> create(AccountDto accountDto){

        Optional<AccountTrxEntity> optionalEntity = accountRepository.findFirstByAccountNumber(accountDto.getAccountNumber());
        AccountTrxEntity accountTrxEntity = null;

        if(optionalEntity.isPresent()){
            accountTrxEntity = optionalEntity.get();
            accountTrxEntity.setBalance(accountDto.getBalance());
        }else{
            accountTrxEntity = accountDto.toEntity();
            accountTrxEntity.setTrxType(TransactionType.BALANCE_UPDATE.getValue());
        }

        accountTrxEntity.setLastUpdateTimestamp(new Timestamp(System.currentTimeMillis()));
        return ResponseEntity.status(HttpStatus.OK).body(accountRepository.save(accountTrxEntity).toDto());
    }

    @Override
    public ResponseEntity<ResponseDto> postTransaction(TransactionDto transactionDto) {

        Optional<AccountTrxEntity> optionalEntity = accountRepository.findLatestTransactionForAccount(transactionDto.getAccountNumber());

        if(!optionalEntity.isPresent()){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorResponseDto("Invalid account number!"));
        }
        /*VALIDATE FOR TRANSACTION TYPE*/
        if(!TransactionType.exists(transactionDto.getType().toUpperCase(Locale.ROOT))){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorResponseDto("Invalid Trx type!"));
        }

        AccountTrxEntity accountTrxEntity = new AccountTrxEntity();
        accountTrxEntity.setAccountNumber(transactionDto.getAccountNumber());
        accountTrxEntity.setTrxType(transactionDto.getType());
        accountTrxEntity.setLastUpdateTimestamp(transactionDto.getTransactionTs());

        if(TransactionType.DEPOSIT.getValue().equalsIgnoreCase(transactionDto.getType())){
            accountTrxEntity.setBalance(optionalEntity.get().getBalance().add(transactionDto.getAmount()));
        }else{
            accountTrxEntity.setBalance(optionalEntity.get().getBalance().subtract(transactionDto.getAmount()));
        }

        return ResponseEntity.status(HttpStatus.OK).body(accountRepository.save(accountTrxEntity).toDto());
    }

    @Override
    public ResponseEntity<ResponseDto> getLatestBalance(String accountNumber) {
        Optional<AccountTrxEntity> optionalEntity = accountRepository.findLatestTransactionForAccount(accountNumber);

        if(!optionalEntity.isPresent()){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorResponseDto("Invalid account number!"));
        }
        return ResponseEntity.status(HttpStatus.OK).body(optionalEntity.get().toAccountBalanceDto());
    }

    @Override
    public ResponseEntity<List<ResponseDto>> getTransactions(Map<String , String> httpHeaders, String accountNumber) {

        List<AccountTrxEntity> accountTrxEntities = null;

        /*VALIDATE HEADER PARAMETERS*/
        if(httpHeaders.get(DATE_FROM) == null){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                    Collections.singletonList(new ErrorResponseDto("Invalid header parameter DateFrom!")));
        }

        if(httpHeaders.get(DATE_TO) == null){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                    Collections.singletonList(new ErrorResponseDto("Invalid header parameter DateTo!")));
        }

        String  dateFrom = httpHeaders.get(DATE_FROM).toString();
        String  dateTo = httpHeaders.get(DATE_TO).toString();

        String  type = httpHeaders.get(TYPE) == null ?"":httpHeaders.get(TYPE).toString();

        if(!type.isEmpty()){

            /*VALIDATE FOR TRANSACTION TYPE*/
            if(!TransactionType.exists(type)){
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                        Collections.singletonList(new ErrorResponseDto("Invalid Trx type!")));
            }

            accountTrxEntities =  accountRepository.
                    findAllTransactionsForAccountAndDateRangeAndType(accountNumber , dateFrom , dateTo , type);
        }else{
            accountTrxEntities =  accountRepository.
                    findAllTransactionsForAccountAndDateRange(accountNumber , dateFrom , dateTo);
        }

        ArrayList<ResponseDto> result = new ArrayList<>();
        accountTrxEntities.forEach((entity->{
            result.add(entity.toDto());
        }));

        return ResponseEntity.status(HttpStatus.OK).body(result);
    }


}


