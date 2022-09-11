package org.kodluyoruz.mybank.model;


import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.kodluyoruz.mybank.controller.AccountController;
import org.kodluyoruz.mybank.controller.CustomerController;
import org.kodluyoruz.mybank.controller.dto.account.AccountCreateDto;
import org.kodluyoruz.mybank.controller.dto.customer.CustomerCreatDto;
import org.kodluyoruz.mybank.controller.dto.customer.CustomerDto;
import org.kodluyoruz.mybank.service.AccountService;
import org.kodluyoruz.mybank.service.CustomerService;
import org.kodluyoruz.mybank.utilities.money.type.MoneyType;
import org.kodluyoruz.mybank.utilities.type.of.account.AccountType;
import org.springframework.test.context.junit4.SpringRunner;
import java.time.LocalDate;
import java.util.UUID;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(SpringRunner.class)
public class AccountTest {


    private AccountController accountController;
    private AccountService accountService;

    UUID uuid  = UUID.randomUUID();


    private final AccountCreateDto accountCreateDto = new AccountCreateDto(CustomerDto.builder().build(), uuid,2000, AccountType.ACCUMULATION_ACCOUNT, MoneyType.TRY);

    @Test
    public void create(){
        accountService.create(accountCreateDto.toAccount());
        Assertions.assertAll("Customer",
                ()-> assertEquals(accountCreateDto.getIban(),accountController.create(accountCreateDto).getIban()),
                ()-> assertEquals(accountCreateDto.getBalance(),accountController.create(accountCreateDto).getBalance()),
                ()-> assertEquals(accountCreateDto.getAccountType(),accountController.create(accountCreateDto).getAccountType()),
                ()-> assertEquals(accountCreateDto.getMoneyType(),accountController.create(accountCreateDto).getMoneyType())
        );
        assertNotNull(accountController.create(accountCreateDto).getIban());
        assertNotNull(accountController.create(accountCreateDto).getBalance());
        assertNotNull(accountController.create(accountCreateDto).getAccountType());
        assertNotNull(accountController.create(accountCreateDto).getMoneyType());

    }
}
