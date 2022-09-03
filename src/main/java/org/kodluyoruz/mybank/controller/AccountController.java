package org.kodluyoruz.mybank.controller;

import org.kodluyoruz.mybank.controller.Dto.AccountCreatDto;
import org.kodluyoruz.mybank.controller.Dto.AccountDto;
import org.kodluyoruz.mybank.model.Account;
import org.kodluyoruz.mybank.service.AccountService;
import org.kodluyoruz.mybank.service.CustomerService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
    public AccountDto creat(@RequestBody AccountCreatDto dto){
        Account account = customerService.getPagesOfCustomer(dto.);

    }
}
