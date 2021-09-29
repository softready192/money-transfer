package com.bsf.transfer.repository;

import com.bsf.transfer.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<Account, Long> {

    Account findByAccountNumber(String accountNumber);

    boolean existsAccountByAccountNumber(String accountNumber);
}
