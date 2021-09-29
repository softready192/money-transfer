package com.bsf.transfer.model;

import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotBlank;

public class TransferRequest {

    @ApiModelProperty("Transaction Amount")
    private double amount;

    @NotBlank(message = "description is mandatory")
    @ApiModelProperty("Transaction description")
    private String description;

    @NotBlank(message = "sourceAccountNumber is mandatory")
    @ApiModelProperty("Source account number")
    private String sourceAccountNumber;

    @NotBlank(message = "targetAccountNumber is mandatory")
    @ApiModelProperty("Target account number")
    private String targetAccountNumber;

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
