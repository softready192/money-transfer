package com.bsf.transfer.model;

import javax.validation.constraints.NotBlank;

public class TransferRequest {

    private double amount;

    @NotBlank(message = "description is mandatory")
    private String description;

    @NotBlank(message = "sourceAccountNumber is mandatory")
    private String sourceAccountNumber;

    @NotBlank(message = "targetAccountNumber is mandatory")
    private String targetAccountNumber;

    public TransferRequest() {
    }

    public TransferRequest(double amount, String description, String sourceAccountNumber, String targetAccountNumber) {
        this.amount = amount;
        this.description = description;
        this.sourceAccountNumber = sourceAccountNumber;
        this.targetAccountNumber = targetAccountNumber;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getSourceAccountNumber() {
        return sourceAccountNumber;
    }

    public void setSourceAccountNumber(String sourceAccountNumber) {
        this.sourceAccountNumber = sourceAccountNumber;
    }

    public String getTargetAccountNumber() {
        return targetAccountNumber;
    }

    public void setTargetAccountNumber(String targetAccountNumber) {
        this.targetAccountNumber = targetAccountNumber;
    }
}
