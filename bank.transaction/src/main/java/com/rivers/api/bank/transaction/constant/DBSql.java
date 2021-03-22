package com.rivers.api.bank.transaction.constant;

public interface DBSql {

    String FETCH_BALANCE_FOR_ACC_NO = "SELECT  e.* FROM account_trx e  " +
            "WHERE e.account_number=:accountNumber order by e.id desc limit 1";

    String FIND_ALL_TRANSACTION_FOR_ACCOUNT_WITH_RANGE = "SELECT e.* FROM account_trx e " +
            "WHERE e.account_number=:accountNumber and " +
            "last_update_timestamp between :dateFrom and :dateTo order by e.id asc";

    String FIND_ALL_TRANSACTION_FOR_ACCOUNT_WITH_RANGE_AND_TYPE = "SELECT e.* FROM account_trx e " +
            "WHERE e.account_number=:accountNumber and " +
            "last_update_timestamp between :dateFrom and :dateTo and trx_type =:type  order by e.id asc";

}
