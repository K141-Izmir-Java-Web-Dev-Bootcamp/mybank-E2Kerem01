package org.kodluyoruz.mybank.service;

import org.kodluyoruz.mybank.controller.dto.bankcard.BankCardDto;
import org.kodluyoruz.mybank.model.BankCard;
import org.kodluyoruz.mybank.repository.BankCardRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class BankCardService {

    private final BankCardRepository bankCardRepository;

    public BankCardService(BankCardRepository bankCardRepository) {
        this.bankCardRepository = bankCardRepository;
    }

    public BankCard create(BankCard bankCard){
        return bankCardRepository.save(bankCard);
    }

    public void deleteAccount(Long id) {
        bankCardRepository.deleteById(id);
    }

    public Page<BankCard> getPagesOfBankCards(Pageable pageable) {
        return bankCardRepository.findAll(pageable);
    }

    public Optional<BankCard> getBankCard(Long id){
        return bankCardRepository.findById(id);
    }
}
