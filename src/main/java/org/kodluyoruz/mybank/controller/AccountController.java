package org.kodluyoruz.mybank.controller;
import org.kodluyoruz.mybank.controller.dto.account.AccountCreateDto;
import org.kodluyoruz.mybank.controller.dto.account.AccountDto;
import org.kodluyoruz.mybank.model.Account;
import org.kodluyoruz.mybank.service.AccountService;
import org.kodluyoruz.mybank.service.CustomerService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;
import java.util.UUID;


@RestController
@RequestMapping("/api/v1/")
public class AccountController {

    private final CustomerService customerService;
    private final AccountService accountService;

    public AccountController(CustomerService customerService, AccountService accountService) {
        this.customerService = customerService;
        this.accountService = accountService;
    }


    @PostMapping("account")
    @ResponseStatus(HttpStatus.CREATED)
    public AccountDto create(@RequestBody AccountCreateDto accountCreateDto) {
        Account account = accountService.create(accountCreateDto.toAccount());
        return AccountDto.builder()
                .accountId(account.getAccountId())
                .balance(account.getBalance())
                .accountType(account.getAccountType())
                .moneyType(account.getMoneyType())
                .iban(account.getIban())
                .build();
    }

    @DeleteMapping("account/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteAccount(@PathVariable Long id){
            accountService.deleteAccount(id);
    }

    @GetMapping("accounts")
    public Page<AccountDto> listAccounts(Pageable pageable){
        return accountService.getPagesOfAccount(pageable)
                .map(account -> AccountDto.builder()
                        .accountId(account.getAccountId())
                        .accountType(account.getAccountType())
                        .balance(account.getBalance())
                        .iban(account.getIban())
                        .moneyType(account.getMoneyType())
                        .build());
    }

    @GetMapping("acccount/{id}")
    @ResponseStatus(HttpStatus.FOUND)
    public Optional<AccountDto> getAccountById(@PathVariable Long id){
        try {
            return accountService.getAccount(id)
                    .map(account -> AccountDto.builder()
                            .accountId(account.getAccountId())
                            .accountType(account.getAccountType())
                            .balance(account.getBalance())
                            .iban(account.getIban())
                            .moneyType(account.getMoneyType())
                            .build());
        }catch (Exception e){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Account not fount");
        }

    }

    @PostMapping("withdrawMoneyWithQrCode/{accountId}/{amount}")
    public void withdrawMoneyWithQrCode(@PathVariable Long accountId,@PathVariable double amount){
        accountService.withdrawMoneyWithQrCode(accountId,amount);
    }

    @PostMapping("depositMoneyWithQrCode/{accountId}/{amount}")
    public void depositMoneyWithQrCode(@PathVariable Long accountId,@PathVariable double amount){
        accountService.depositMoneyWithQrCode(accountId,amount);
    }

    /*@PostMapping("Remittance/{amount}/{senderIban}/{receiverIban}")
    public void transferToIban(@PathVariable double amount, @PathVariable UUID senderIban, @PathVariable UUID receiverIban){
        accountService.transferToIban(amount,senderIban,receiverIban);
    }*/





}
