package com.learning.transactions.model;

import com.learning.transactions.model.TransactionStatus;

import java.math.BigDecimal;

public class Transaction
{
    private Long id;
    private TransactionStatus status;
    private BigDecimal amount;

    public  Transaction(Long id,TransactionStatus status, BigDecimal amount)
    {
        this.id = id;
        this.status = status;
        this.amount = amount;
    }
}