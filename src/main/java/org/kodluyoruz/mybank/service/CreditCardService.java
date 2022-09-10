package org.kodluyoruz.mybank.service;

import org.kodluyoruz.mybank.controller.dto.creditcard.CreditCardDto;
import org.kodluyoruz.mybank.model.BankCard;
import org.kodluyoruz.mybank.model.CreditCard;
import org.kodluyoruz.mybank.repository.CreditCardRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Objects;
import java.util.Optional;

@Service
public class CreditCardService {

    private final CreditCardRepository creditCardRepository;

    public CreditCardService(CreditCardRepository creditCardRepository) {
        this.creditCardRepository = creditCardRepository;
    }

    public CreditCard create(CreditCard creditCard) {
        return creditCardRepository.save(creditCard);
    }

    public void deleteCreditCard(Long id) {
        creditCardRepository.deleteById(id);
    }

    public Page<CreditCard> getPagesOfCreditCards(Pageable pageable) {
        return creditCardRepository.findAll(pageable);
    }

    public Optional<CreditCard> getCreditCard(Long id){
        return creditCardRepository.findById(id);
    }
    public void onlineShoppingProcess(Long creditCardId, String creditCardPassword, String creditCardCvc, double amount) {

        CreditCard creditCard = creditCardRepository.findByCreditCardId(creditCardId);

        if ((!Objects.equals(creditCard.getCreditCardPassword(), creditCardPassword)) && (!Objects.equals(creditCard.getCreditCardCvc(), creditCardCvc))){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Password or cvc are wrong.");
        } else{
            if (creditCard.getCreditCardLimit()<amount){
                throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Not enough limit");
            }else {
                creditCard.setCreditCardLimit(creditCard.getCreditCardLimit()-amount);
                creditCard.setAmountOfDebt(creditCard.getAmountOfDebt()+amount);
            }
        }

    }


}
