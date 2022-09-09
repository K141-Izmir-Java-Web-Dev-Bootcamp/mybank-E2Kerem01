package org.kodluyoruz.mybank.service;

import org.kodluyoruz.mybank.controller.dto.creditcard.CreditCardDto;
import org.kodluyoruz.mybank.model.CreditCard;
import org.kodluyoruz.mybank.repository.CreditCardRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

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
}
