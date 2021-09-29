package com.bsf.transfer.service;

import com.bsf.transfer.entity.Account;
import com.bsf.transfer.model.AccountRequest;

import java.util.List;

public interface AccountService {

    Account save(AccountRequest accountRequest);

    List<Account> getAll();

    Account findAccountByAccountNumber(String accountNumber);

    Account update(Account account);

}
