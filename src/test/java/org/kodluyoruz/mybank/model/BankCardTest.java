package org.kodluyoruz.mybank.model;

import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.kodluyoruz.mybank.controller.BankCardController;
import org.kodluyoruz.mybank.controller.dto.account.AccountDto;
import org.kodluyoruz.mybank.controller.dto.bankcard.BankCardCreateDto;
import org.kodluyoruz.mybank.service.BankCardService;
import org.springframework.test.context.junit4.SpringRunner;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(SpringRunner.class)
public class BankCardTest {


    private BankCardService bankCardService;

    private BankCardController bankCardController;


    private final BankCardCreateDto bankCardCreateDto = new BankCardCreateDto(AccountDto.builder().build(),2000,"1234","1234");

    @Test
    public void create(){
        bankCardService.create(bankCardCreateDto.toBankCard());
        Assertions.assertAll("BankCard",
                ()-> assertEquals(bankCardCreateDto.getBankCardLimit(),bankCardController.create(bankCardCreateDto).getBankCardLimit()),
                ()-> assertEquals(bankCardCreateDto.getBankCardPassword(),bankCardController.create(bankCardCreateDto).getBankCardPassword()),
                ()-> assertEquals(bankCardCreateDto.getBankCardCvc(),bankCardController.create(bankCardCreateDto).getBankCardCvc())

        );
        assertNotNull(bankCardController.create(bankCardCreateDto).getBankCardLimit());
        assertNotNull(bankCardController.create(bankCardCreateDto).getBankCardPassword());
        assertNotNull(bankCardController.create(bankCardCreateDto).getBankCardCvc());

    }
}
