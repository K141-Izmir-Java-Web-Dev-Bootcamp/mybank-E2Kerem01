package org.kodluyoruz.mybank.controller;

import org.kodluyoruz.mybank.controller.dto.bankcard.BankCardCreateDto;
import org.kodluyoruz.mybank.controller.dto.creditcard.CreditCardCreateDto;
import org.kodluyoruz.mybank.controller.dto.creditcard.CreditCardDto;
import org.kodluyoruz.mybank.model.BankCard;
import org.kodluyoruz.mybank.model.CreditCard;
import org.kodluyoruz.mybank.service.CreditCardService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/api/v1/")
public class CreditCardController {

    private final CreditCardService creditCardService;

    public CreditCardController(CreditCardService creditCardService) {
        this.creditCardService = creditCardService;
    }

    @PostMapping("creditcard")
    @ResponseStatus(HttpStatus.CREATED)
    private CreditCardDto create(@RequestBody CreditCardCreateDto creditCardCreateDto){
        CreditCard creditCard = creditCardService.create(creditCardCreateDto.toCreditCard());
        return CreditCardDto.builder()
                .creditCard_id(creditCard.getCreditCard_id())
                .creditCardPassword(creditCard.getCreditCardPassword())
                .creditCardLimit(creditCard.getCreditCardLimit())
                .creditCcv(creditCard.getCreditCcv())
                .account(creditCard.getAccount().toAccountDto())
                .build();
    }

    @DeleteMapping("creditcard/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteCreditCard(@PathVariable Long id){
        try {
            creditCardService.deleteCreditCard(id);
        }catch (Exception e){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Account not fount");
        }

    }

    @GetMapping("creditcards")
    public Page<CreditCardDto> listCreditCards(Pageable pageable){
        return creditCardService.getPagesOfCreditCards(pageable)
                .map(creditCard -> CreditCardDto.builder()
                        .creditCard_id(creditCard.getCreditCard_id())
                        .creditCardPassword(creditCard.getCreditCardPassword())
                        .creditCardLimit(creditCard.getCreditCardLimit())
                        .creditCcv(creditCard.getCreditCcv())
                        .build());

    }
}
