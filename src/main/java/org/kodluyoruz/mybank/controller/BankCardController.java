package org.kodluyoruz.mybank.controller;

import org.kodluyoruz.mybank.controller.dto.account.AccountDto;
import org.kodluyoruz.mybank.controller.dto.bankcard.BankCardCreateDto;
import org.kodluyoruz.mybank.controller.dto.bankcard.BankCardDto;
import org.kodluyoruz.mybank.model.BankCard;
import org.kodluyoruz.mybank.service.BankCardService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@RestController
@RequestMapping("/api/v1/")
public class BankCardController {


    private final BankCardService bankCardService;

    public BankCardController(BankCardService bankCardService) {
        this.bankCardService = bankCardService;
    }

    @PostMapping("bankcard")
    @ResponseStatus(HttpStatus.CREATED)
    private BankCardDto create(@RequestBody BankCardCreateDto bankCardDto){
        BankCard bankCard = bankCardService.create(bankCardDto.toBankCard());
        return BankCardDto.builder()
                .bankCardId(bankCard.getBankCardId())
                .bankCardLimit(bankCard.getBankCardLimit())
                .bankCardPassword(bankCard.getBankCardPassword())
                .bankCardCcv(bankCard.getBankCardCcv())
                .build();
    }

    @DeleteMapping("bankcard/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteBankCard(@PathVariable Long id){
        try {
            bankCardService.deleteAccount(id);
        }catch (Exception e){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Account not fount");
        }
    }

    @GetMapping("bankcards")
    public Page<BankCardDto> listBankCards(Pageable pageable){
        return bankCardService.getPagesOfBankCards(pageable)
                .map(bankCard -> BankCardDto.builder()
                        .bankCardId(bankCard.getBankCardId())
                        .bankCardLimit(bankCard.getBankCardLimit())
                        .bankCardPassword(bankCard.getBankCardPassword())
                        .bankCardCcv(bankCard.getBankCardCcv())
                        .build());
    }


    @GetMapping("bankcard/{id}")
    @ResponseStatus(HttpStatus.FOUND)
    public Optional<BankCardDto> getBankCardId(@PathVariable Long id){
        try {
            return bankCardService.getBankCard(id)
                    .map(bankCard -> BankCardDto.builder()
                            .bankCardId(bankCard.getBankCardId())
                            .bankCardLimit(bankCard.getBankCardLimit())
                            .bankCardPassword(bankCard.getBankCardPassword())
                            .bankCardCcv(bankCard.getBankCardCcv())
                            .build());
        }catch (Exception e){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Account not fount");
        }

    }



}
