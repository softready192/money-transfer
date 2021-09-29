package com.bsf.transfer.service.impl;

import com.bsf.transfer.entity.Account;
import com.bsf.transfer.exception.InsufficientBalanceException;
import com.bsf.transfer.exception.InvalidAccountException;
import com.bsf.transfer.model.TransferRequest;
import com.bsf.transfer.service.AccountService;
import com.bsf.transfer.service.TransferService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.mockito.Mock;
import org.mockito.Mockito;

import static java.util.Objects.requireNonNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;
import static org.mockito.MockitoAnnotations.openMocks;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class TransferServiceImplTest {

    @Mock
    private AccountService accountService;

    private TransferService transferService;

    @BeforeAll
    public void setUp() {
        openMocks(this);
        transferService = new TransferServiceImpl(accountService);
    }

    @Test
    public void testTransferWhenSourceAndTargetSame() {
        InvalidAccountException thrown = assertThrows(
                InvalidAccountException.class,
                () -> transferService.transfer(
                        new TransferRequest(10D, "Transfer", "Source", "Source"))
        );
        assertEquals("Source and Target accounts are same.", thrown.getMessage());
    }

    @Test
    public void testTransferWhenSourceAccountNotPresent() {
        when(accountService.findAccountByAccountNumber("Source")).thenReturn(null);
        InvalidAccountException thrown = assertThrows(
                InvalidAccountException.class,
                () -> transferService.transfer(
                        new TransferRequest(10D, "Transfer", "Source", "Target"))
        );
        assertEquals("Invalid Source Account.", thrown.getMessage());
    }

    @Test
    public void testTransferWhenSourceTargetNotPresent() {
        Account account = mock(Account.class);
        when(accountService.findAccountByAccountNumber("Source")).thenReturn(account);
        when(accountService.findAccountByAccountNumber("Target")).thenReturn(null);
        InvalidAccountException thrown = assertThrows(
                InvalidAccountException.class,
                () -> transferService.transfer(
                        new TransferRequest(10D, "Transfer", "Source", "Target"))
        );
        assertEquals("Invalid Target Account.", thrown.getMessage());
    }

    @Test
    public void testTransferWhenSourceHaveInsufficientBalance() {
        Account account = mock(Account.class);
        when(account.getBalance()).thenReturn(5D);
        when(accountService.findAccountByAccountNumber("Source")).thenReturn(account);
        when(accountService.findAccountByAccountNumber("Target")).thenReturn(Mockito.mock(Account.class));
        InsufficientBalanceException thrown = assertThrows(
                InsufficientBalanceException.class,
                () -> transferService.transfer(
                        new TransferRequest(10D, "Transfer", "Source", "Target"))
        );
        assertEquals("Insufficient balance to transfer from source account.", thrown.getMessage());
    }

    @Test
    public void testTransferSuccess() {
        Account sourceAccount = new Account();
        sourceAccount.setId(1L);
        sourceAccount.setAccountNumber("Source");
        sourceAccount.setBalance(50D);

        Account targetAccount = new Account();
        targetAccount.setId(2L);
        targetAccount.setAccountNumber("Target");
        targetAccount.setBalance(50D);

        when(accountService.findAccountByAccountNumber("Source")).thenReturn(sourceAccount);
        when(accountService.findAccountByAccountNumber("Target")).thenReturn(targetAccount);
        transferService.transfer(
                new TransferRequest(10D, "Transfer", "Source", "Target"));
        verify(accountService, times(2)).update(any(Account.class));
        assertEquals(60D, targetAccount.getBalance());
        Assertions.assertNotNull(targetAccount.getTransactionDetails());
        assertEquals(1, targetAccount.getTransactionDetails().size());
        assertEquals("Credit", requireNonNull(targetAccount.getTransactionDetails().stream().findFirst().orElse(null)).getTransactionType());

        assertEquals(40D, sourceAccount.getBalance());
        Assertions.assertNotNull(sourceAccount.getTransactionDetails());
        assertEquals(1, sourceAccount.getTransactionDetails().size());
        assertEquals("Debit", requireNonNull(sourceAccount.getTransactionDetails().stream().findFirst().orElse(null)).getTransactionType());
    }

}
