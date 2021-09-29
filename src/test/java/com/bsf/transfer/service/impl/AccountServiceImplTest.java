package com.bsf.transfer.service.impl;


import com.bsf.transfer.entity.Account;
import com.bsf.transfer.exception.AccountExistsException;
import com.bsf.transfer.model.AccountRequest;
import com.bsf.transfer.repository.AccountRepository;
import com.bsf.transfer.service.AccountService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.mockito.Mock;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.openMocks;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class AccountServiceImplTest {

    @Mock
    private AccountRepository accountRepository;

    private AccountService accountService;

    @BeforeAll
    public void setUp() {
        openMocks(this);
        accountService = new AccountServiceImpl(accountRepository);
    }

    @Test
    public void testSave() {
        AccountRequest account = getAccountRequest("123");
        Account dbObject = Mockito.mock(Account.class);
        dbObject.setId(1L);
        when(accountRepository.save(Mockito.any(Account.class))).thenReturn(dbObject);
        Account response = accountService.save(account);
        assertEquals(response.getId(), dbObject.getId());
    }

    @Test
    public void testSaveWithSameAccountNumber() {
        AccountRequest account = getAccountRequest("123");
        Account dbObject = Mockito.mock(Account.class);
        dbObject.setId(1L);
        when(accountRepository.existsAccountByAccountNumber("123")).thenReturn(true);

        AccountExistsException thrown = assertThrows(
                AccountExistsException.class,
                () -> accountService.save(account)
        );
        assertEquals("Account already exists.", thrown.getMessage());
    }

    @Test
    public void testGetAll() {
        List<Account> accounts = new ArrayList<>();
        Account account = getAccount("123");
        accounts.add(account);
        when(accountRepository.findAll()).thenReturn(accounts);
        List<Account> response = accountService.getAll();
        Assertions.assertFalse(response.isEmpty());
        assertEquals(1, response.size());
    }

    @Test
    public void testGetAllWithNoRecords() {
        when(accountRepository.findAll()).thenReturn(new ArrayList<>());
        List<Account> response = accountService.getAll();
        assertTrue(response.isEmpty());
    }

    @Test
    public void testFindByAccountNumber() {
        Account account = getAccount("test");
        account.setId(2L);
        when(accountRepository.findByAccountNumber("test")).thenReturn(account);
        Account response = accountService.findAccountByAccountNumber("test");
        assertEquals("test", response.getAccountNumber());
        assertEquals(2L, response.getId());
    }

    @Test
    public void testFindByAccountNumberWithWrongAccountNumber() {
        Account account = getAccount("test");
        account.setId(2L);
        when(accountRepository.findByAccountNumber("test2")).thenReturn(account);
        Account response = accountService.findAccountByAccountNumber("test");
        Assertions.assertNull(response);
    }

    private AccountRequest getAccountRequest(String test) {
        AccountRequest accountRequest = new AccountRequest();
        accountRequest.setAccountNumber(test);
        accountRequest.setBalance(10d);
        accountRequest.setName("TestUser1");
        return accountRequest;
    }

    private Account getAccount(String test) {
        Account account = new Account();
        account.setAccountNumber(test);
        account.setBalance(10d);
        account.setName("TestUser1");
        return account;
    }
}
