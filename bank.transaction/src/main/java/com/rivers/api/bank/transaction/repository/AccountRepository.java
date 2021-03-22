package com.rivers.api.bank.transaction.repository;

import com.rivers.api.bank.transaction.constant.DBSql;
import com.rivers.api.bank.transaction.entity.AccountTrxEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AccountRepository extends JpaRepository<AccountTrxEntity, Integer> {

    Optional<AccountTrxEntity> findFirstByAccountNumber(String accountNumber);

    @Query(value = DBSql.FETCH_BALANCE_FOR_ACC_NO, nativeQuery = true)
    Optional<AccountTrxEntity> findLatestTransactionForAccount(@Param("accountNumber") String accountNumber);

    @Query(value = DBSql.FIND_ALL_TRANSACTION_FOR_ACCOUNT_WITH_RANGE,nativeQuery = true)
    List<AccountTrxEntity> findAllTransactionsForAccountAndDateRange(
            @Param("accountNumber") String accountNumber ,
            @Param("dateFrom") String dateFrom,
            @Param("dateTo")String dateTo);

    @Query(value = DBSql.FIND_ALL_TRANSACTION_FOR_ACCOUNT_WITH_RANGE_AND_TYPE,nativeQuery = true)
    List<AccountTrxEntity> findAllTransactionsForAccountAndDateRangeAndType(
            @Param("accountNumber") String accountNumber ,
            @Param("dateFrom") String dateFrom,
            @Param("dateTo")String dateTo,
            @Param("type") String type);
}
