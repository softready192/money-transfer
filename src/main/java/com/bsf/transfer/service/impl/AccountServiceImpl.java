package com.bsf.transfer.service.impl;

import com.bsf.transfer.entity.Account;
import com.bsf.transfer.entity.TransactionDetails;
import com.bsf.transfer.enums.TransactionType;
import com.bsf.transfer.exception.AccountExistsException;
import com.bsf.transfer.repository.AccountRepository;
import com.bsf.transfer.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public class AccountServiceImpl implements AccountService {

    private final AccountRepository accountRepository;

    @Autowired
    public AccountServiceImpl(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @Override
    public Account save(Account account) {
        if (null == account.getId()) {
            if(accountRepository.existsAccountByAccountNumber(account.getAccountNumber())) {
                throw new AccountExistsException("Account already exists.");
            }
            saveInitialTransaction(account);
        }
        return accountRepository.save(account);
    }

    private void saveInitialTransaction(Account account) {
        TransactionDetails transactionDetails = new TransactionDetails();
        transactionDetails.setDescription("Opening Balance");
        transactionDetails.setTransactionAmount(account.getBalance());
        transactionDetails.setAccount(account);
        transactionDetails.setTransactionType(TransactionType.Credit.name());
        Set<TransactionDetails> accountTransactionDetails = account.getTransactionDetails();
        accountTransactionDetails.add(transactionDetails);
        account.setTransactionDetails(accountTransactionDetails);
    }

    @Override
    public List<Account> getAll() {
        return accountRepository.findAll();
    }

    @Override
    public Account findAccountByAccountNumber(String accountNumber) {
        return accountRepository.findByAccountNumber(accountNumber);
    }
}
