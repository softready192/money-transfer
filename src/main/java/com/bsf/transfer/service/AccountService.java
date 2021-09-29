package com.bsf.transfer.service;

import com.bsf.transfer.entity.Account;

import java.util.List;

public interface AccountService {

    Account save(Account account);

    List<Account> getAll();

    Account findAccountByAccountNumber(String accountNumber);

}
