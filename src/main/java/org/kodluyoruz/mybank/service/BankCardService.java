package org.kodluyoruz.mybank.service;


import org.kodluyoruz.mybank.model.BankCard;
import org.kodluyoruz.mybank.repository.BankCardRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Objects;
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

    public void withdrawMoneyBankCardFromAtm(Long bankCardId, String bankCardPassword, double amount) {
        BankCard bankCard = bankCardRepository.findByBankCardId(bankCardId);

        if ((!Objects.equals(bankCard.getBankCardPassword(), bankCardPassword))){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Password is wrong.");
        } else{
            if (bankCard.getBankCardLimit()<amount){
                throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Amount is bigger than card limit.");
            }else {
                bankCard.setBankCardLimit(bankCard.getBankCardLimit()-amount);
                bankCardRepository.save(bankCard);
                throw new ResponseStatusException(HttpStatus.OK,"Process is success...");
            }
        }


    }

    public void depositMoneyBankCardAtAtm(Long bankCardId, String bankCardPassword, double amount) {
        BankCard bankCard = bankCardRepository.findByBankCardId(bankCardId);

        if ((!Objects.equals(bankCard.getBankCardPassword(), bankCardPassword))){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Password is wrong.");
        } else{
            if (amount==0){
                throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Please enter bigger than 0.");
            }else {
                bankCard.setBankCardLimit(bankCard.getBankCardLimit()+amount);
                bankCardRepository.save(bankCard);
                throw new ResponseStatusException(HttpStatus.OK,"Process is success...");
            }
        }
    }


    public void onlineShoppingBankCard(Long bankCardId, String bankCardPassword, String bankCardCvc, double amount) {
        BankCard bankCard = bankCardRepository.findByBankCardId(bankCardId);

        if ((!Objects.equals(bankCard.getBankCardPassword(), bankCardPassword)) && (!Objects.equals(bankCard.getBankCardCvc(), bankCardCvc))){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Password is wrong.");
        } else{
            if (amount==0 || bankCard.getBankCardLimit()<amount){
                throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Please enter bigger than 0.");
            }else {
                bankCard.setBankCardLimit(bankCard.getBankCardLimit()-amount);
                bankCardRepository.save(bankCard);
                throw new ResponseStatusException(HttpStatus.OK,"Process is success...");
            }
        }
    }
}
