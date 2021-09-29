package com.bsf.transfer.controller;

import com.bsf.transfer.entity.Account;
import com.bsf.transfer.model.AccountRequest;
import com.bsf.transfer.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/accounts")
public class AccountController {

    private final AccountService accountService;

    @Autowired
    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @PostMapping
    public ResponseEntity<Void> save(@RequestBody @Valid AccountRequest accountRequest) {
        accountService.save(accountRequest);
        return ResponseEntity.ok().build();
    }

    @GetMapping
    public ResponseEntity<List<Account>> getAll() {
        return ResponseEntity.ok(accountService.getAll());
    }

    @GetMapping(value = "/{accountNumber}")
    public ResponseEntity<Account> getByAccountNumber(@PathVariable("accountNumber") String accountNumber) {
        return ResponseEntity.ok(accountService.findAccountByAccountNumber(accountNumber));
    }
}
