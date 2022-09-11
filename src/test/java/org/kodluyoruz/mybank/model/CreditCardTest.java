package org.kodluyoruz.mybank.model;


import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.kodluyoruz.mybank.controller.CreditCardController;
import org.kodluyoruz.mybank.controller.dto.account.AccountDto;
import org.kodluyoruz.mybank.controller.dto.creditcard.CreditCardCreateDto;
import org.kodluyoruz.mybank.service.CreditCardService;
import org.springframework.test.context.junit4.SpringRunner;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(SpringRunner.class)
public class CreditCardTest {


    private CreditCardService creditCardService;
    private CreditCardController creditCardController;

    private final CreditCardCreateDto creditCardCreateDto = new CreditCardCreateDto(AccountDto.builder().build(),2000,"1234","1234");


    @Test
    public void create(){
        creditCardService.create(creditCardCreateDto.toCreditCard());
        Assertions.assertAll("CreditCard",
                ()-> assertEquals(creditCardCreateDto.getCreditCardLimit(),creditCardController.create(creditCardCreateDto).getCreditCardLimit()),
                ()-> assertEquals(creditCardCreateDto.getCreditCardPassword(),creditCardController.create(creditCardCreateDto).getCreditCardPassword()),
                ()-> assertEquals(creditCardCreateDto.getCreditCardCvc(),creditCardController.create(creditCardCreateDto).getCreditCardCvc())

        );
        assertNotNull(creditCardController.create(creditCardCreateDto).getCreditCardLimit());
        assertNotNull(creditCardController.create(creditCardCreateDto).getCreditCardPassword());
        assertNotNull(creditCardController.create(creditCardCreateDto).getCreditCardCvc());

    }
}
