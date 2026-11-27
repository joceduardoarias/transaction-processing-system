package com.learning.transactions.service;

import com.learning.transactions.model.Transaction;
import com.learning.transactions.model.TransactionStatus;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TransactionServiceTest {

    @Test
    public void shouldReturnOnlyApprovedTransactions(){

        Transaction transaction1 = new Transaction(1L, TransactionStatus.APPROVED, new BigDecimal("1200.00"));
        Transaction transaction2 = new Transaction(2L, TransactionStatus.REJECTED, new BigDecimal("15000.00"));
        Transaction transaction3 = new Transaction(3L, TransactionStatus.APPROVED, new BigDecimal("16000.00"));

        List<Transaction> transactionList = Arrays.asList(transaction1, transaction2, transaction3);

        TransactionService transactionService = new TransactionService();

        List<Transaction> approvedTransactions = transactionService.getApprovedTransactions(transactionList);
        assertEquals(2, approvedTransactions.size());

        for (Transaction approvedTransaction : approvedTransactions) {
            assertEquals(TransactionStatus.APPROVED, approvedTransaction.getStatus());
        }
        assertSame(transaction1, approvedTransactions.get(0));
        assertSame(transaction3, approvedTransactions.get(1));
    }

    @Test
    public void shouldReturnEmptyListWhenNoTransactionsAreApproved(){

        Transaction transaction1 = new Transaction(1L, TransactionStatus.REJECTED, new BigDecimal("1200.00"));
        Transaction transaction2 = new Transaction(2L, TransactionStatus.REJECTED, new BigDecimal("15000.00"));
        Transaction transaction3 = new Transaction(3L, TransactionStatus.PENDING, new BigDecimal("12030.00"));

        List<Transaction> transactionList = Arrays.asList(transaction1, transaction2, transaction3);

        TransactionService transactionService = new TransactionService();

        List<Transaction> transactions = transactionService.getApprovedTransactions(transactionList);

        assertTrue(transactions.isEmpty());
    }

    @Test
    public void shouldNotModifyOriginalTransactionList(){
        Transaction transaction1 = new Transaction(new Long(1), TransactionStatus.APPROVED, new BigDecimal("1200.00"));
        Transaction transaction2 = new Transaction(new Long(2), TransactionStatus.REJECTED, new BigDecimal("15000.00"));
        Transaction transaction3 = new Transaction(new Long(3), TransactionStatus.PENDING, new BigDecimal("12030.00"));

        List<Transaction> transactionList = new ArrayList<>(Arrays.asList(transaction1, transaction2, transaction3));
        List<Transaction> originalSnapshot = new ArrayList<>(transactionList);
        TransactionService transactionService = new TransactionService();
        transactionService.getApprovedTransactions(transactionList);
        assertEquals(originalSnapshot, transactionList);
    }
}
