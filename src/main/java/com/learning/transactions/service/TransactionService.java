package com.learning.transactions.service;


import com.learning.transactions.model.Transaction;
import com.learning.transactions.model.TransactionStatus;

import java.util.List;
import java.util.stream.Collectors;

public class TransactionService {

    public List<Transaction> getApprovedTransactions(List<Transaction> transactions) {
        List<Transaction> approvedTransactions = transactions.stream().
                filter(transaction -> transaction.getStatus() == TransactionStatus.APPROVED)
                .collect(Collectors.toList());
        return approvedTransactions;
    }
}
