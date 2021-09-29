package com.bsf.transfer.service.impl;

import com.bsf.transfer.entity.Account;
import com.bsf.transfer.entity.TransactionDetails;
import com.bsf.transfer.enums.TransactionType;
import com.bsf.transfer.exception.InsufficientBalanceException;
import com.bsf.transfer.exception.InvalidAccountException;
import com.bsf.transfer.model.TransferRequest;
import com.bsf.transfer.service.AccountService;
import com.bsf.transfer.service.TransferService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;

@Service
public class TransferServiceImpl implements TransferService {

    private final AccountService accountService;

    @Autowired
    public TransferServiceImpl(AccountService accountService) {
        this.accountService = accountService;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void transfer(TransferRequest transferRequest) {
        if (transferRequest.getSourceAccountNumber().equalsIgnoreCase(transferRequest.getTargetAccountNumber()))
            throw new InvalidAccountException("Source and Target accounts are same.");
        Account sourceAccount = accountService.findAccountByAccountNumber(transferRequest.getSourceAccountNumber());
        if (null == sourceAccount)
            throw new InvalidAccountException("Invalid Source Account.");
        Account targetAccount = accountService.findAccountByAccountNumber(transferRequest.getTargetAccountNumber());
        if (null == targetAccount)
            throw new InvalidAccountException("Invalid Target Account.");
        if (sourceAccount.getBalance() < transferRequest.getAmount())
            throw new InsufficientBalanceException("Insufficient balance to transfer from source account.");
        debit(prepareTransactionDetails(transferRequest), sourceAccount);
        credit(prepareTransactionDetails(transferRequest), targetAccount);
    }

    private TransactionDetails prepareTransactionDetails(TransferRequest transferRequest) {
        TransactionDetails transactionDetails = new TransactionDetails();
        transactionDetails.setDescription(transferRequest.getDescription());
        transactionDetails.setTransactionAmount(transferRequest.getAmount());
        return transactionDetails;
    }

    private void updateAccount(TransactionDetails transactionDetails, Account account) {
        transactionDetails.setAccount(account);
        Set<TransactionDetails> transactionDetails1 = account.getTransactionDetails();
        transactionDetails1.add(transactionDetails);
        account.setTransactionDetails(transactionDetails1);
        accountService.update(account);
    }

    private void debit(TransactionDetails transactionDetails, Account sourceAccount) {
        transactionDetails.setTransactionType(TransactionType.Debit.name());
        sourceAccount.setBalance(sourceAccount.getBalance() - transactionDetails.getTransactionAmount());
        updateAccount(transactionDetails, sourceAccount);
    }

    private void credit(TransactionDetails transactionDetails, Account targetAccount) {
        transactionDetails.setTransactionType(TransactionType.Credit.name());
        targetAccount.setBalance(targetAccount.getBalance() + transactionDetails.getTransactionAmount());
        updateAccount(transactionDetails, targetAccount);
    }
}
