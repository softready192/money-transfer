package com.bsf.transfer.controller;

import com.bsf.transfer.model.TransferRequest;
import com.bsf.transfer.service.TransferService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/payment-transfer")
public class PaymentTransferController {

    private final TransferService transferService;

    @Autowired
    public PaymentTransferController(TransferService transferService) {
        this.transferService = transferService;
    }

    @PostMapping
    public ResponseEntity<Void> transfer(@Valid @RequestBody TransferRequest transferRequest) {
        transferService.transfer(transferRequest);
        return ResponseEntity.ok().build();
    }
}
