package org.kodluyoruz.mybank.controller;

import org.kodluyoruz.mybank.controller.dto.creditcard.CreditCardCreateDto;
import org.kodluyoruz.mybank.controller.dto.creditcard.CreditCardDto;
import org.kodluyoruz.mybank.model.CreditCard;
import org.kodluyoruz.mybank.service.CreditCardService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@RestController
@RequestMapping("/api/v1/")
public class CreditCardController {

    private final CreditCardService creditCardService;

    public CreditCardController(CreditCardService creditCardService) {
        this.creditCardService = creditCardService;
    }

    @PostMapping("creditcard")
    @ResponseStatus(HttpStatus.CREATED)
    public CreditCardDto create(@RequestBody CreditCardCreateDto creditCardCreateDto){
        CreditCard creditCard = creditCardService.create(creditCardCreateDto.toCreditCard());
        return CreditCardDto.builder()
                .creditCardId(creditCard.getCreditCardId())
                .creditCardPassword(creditCard.getCreditCardPassword())
                .creditCardLimit(creditCard.getCreditCardLimit())
                .creditCardCvc(creditCard.getCreditCardCvc())
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
                        .amountOfDebt(creditCard.getAmountOfDebt())
                        .creditCardId(creditCard.getCreditCardId())
                        .creditCardPassword(creditCard.getCreditCardPassword())
                        .creditCardLimit(creditCard.getCreditCardLimit())
                        .creditCardCvc(creditCard.getCreditCardCvc())
                        .amountOfDebt(creditCard.getAmountOfDebt())
                        .build());

    }

    @GetMapping("creditcard/{id}")
    @ResponseStatus(HttpStatus.FOUND)
    public Optional<CreditCardDto> getCreditCardId(@PathVariable Long id){
        try {
            return creditCardService.getCreditCard(id)
                    .map(creditCard -> CreditCardDto.builder()
                            .creditCardId(creditCard.getCreditCardId())
                            .creditCardLimit(creditCard.getCreditCardLimit())
                            .amountOfDebt(creditCard.getAmountOfDebt())
                            .creditCardPassword(creditCard.getCreditCardPassword())
                            .creditCardCvc(creditCard.getCreditCardCvc())
                            .build());
        }catch (Exception e){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Credit card not fount");
        }

    }

    @PostMapping("onlineShoppingProcess/{creditCardId}/{creditCardPassword}/{creditCardCvc}/{amount}")
    public void onlineShoppingProcess(@PathVariable Long creditCardId,@PathVariable String creditCardPassword,@PathVariable String creditCardCvc,@PathVariable double amount){
        creditCardService.onlineShoppingProcess(creditCardId,creditCardPassword,creditCardCvc,amount);
    }

    @PostMapping("depositMoneyAtAtm/{creditCardId}/{creditCardPassword}/{amount}")
    public void depositMoneyAtAtm(@PathVariable Long creditCardId,@PathVariable String creditCardPassword,@PathVariable double amount){
        creditCardService.depositMoneyAtAtm(creditCardId,creditCardPassword,amount);
    }

    @PostMapping("withdrawFromAtm/{creditCardId}/{creditCardPassword}/{amount}")
    public void withdrawMoneyFromAtm(@PathVariable Long creditCardId,@PathVariable String creditCardPassword,@PathVariable double amount){
        creditCardService.withdrawMoneyFromAtm(creditCardId,creditCardPassword,amount);
    }

    @PostMapping("upgradeLimit/{creditCardId}/{limit}")
    public void upgradeLimit(@PathVariable Long creditCardId,@PathVariable double limit){
        creditCardService.upgradeLimit(creditCardId,limit);
    }


    @GetMapping("totalDept/{creditCardId}")
    public Optional<CreditCardDto> totalDept(@PathVariable Long id){
        try {
            return creditCardService.getCreditCard(id)
                    .map(creditCard -> CreditCardDto.builder()
                            .creditCardId(creditCard.getCreditCardId())
                            .amountOfDebt(creditCard.getAmountOfDebt())
                            .build());
        }catch (Exception e){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Credit card not fount");
        }

    }


}
