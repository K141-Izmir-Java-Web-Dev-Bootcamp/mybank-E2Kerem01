package org.kodluyoruz.mybank.controller;

import org.kodluyoruz.mybank.service.AccountService;
import org.kodluyoruz.mybank.service.TransferService;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/")
public class TransferController {

    private final TransferService transferService;
    private final AccountService accountService;

    public TransferController(TransferService transferService, AccountService accountService) {
        this.transferService = transferService;
        this.accountService = accountService;
    }


    @PostMapping("Remittance/{amount}/{senderIban}/{receiverIban}")
    public void transferToIban(@PathVariable double amount, @PathVariable UUID senderIban, @PathVariable UUID receiverIban){
        transferService.transferToIban(amount,senderIban,receiverIban);
    }

    @PostMapping("transferBetweenMyAccounts/{iban}/{amount}/{senderAccountType}/{reveiverAccountType}")
    public void transferBetweenMyAccounts(@PathVariable UUID iban,@PathVariable double amount,@PathVariable String senderAccountType,@PathVariable String reveiverAccountType){
        transferService.transferBetweenMyAccounts(iban,amount,senderAccountType,reveiverAccountType);
    }
}
