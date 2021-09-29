package com.bsf.transfer.model;

import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotBlank;

public class AccountRequest {

    @NotBlank(message = "accountNumber is mandatory")
    @ApiModelProperty("Account holder name")
    private String name;

    @NotBlank(message = "accountNumber is mandatory")
    @ApiModelProperty("Account number")
    private String accountNumber;

    @ApiModelProperty("Initial Amount")
    private double balance;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }
}
