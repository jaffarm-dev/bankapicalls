package com.rivers.api.bank.transaction.constant;

import lombok.Getter;

public enum TransactionType {

    BALANCE_UPDATE("BALANCE UPDATE"),
    DEPOSIT("DEPOSIT"),
    WITHDRAW("WITHDRAW");

    @Getter
    private String value;
    private TransactionType(String value){
        this.value=value;
    }

    public static boolean exists(String type){

        boolean exists = false;

        for(TransactionType transactionType : TransactionType.values()){
            if(transactionType.getValue().equalsIgnoreCase(type)){
                exists = true;
                break;
            }
        }
        return exists;
    }
}
