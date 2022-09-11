package org.kodluyoruz.mybank.service;

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
                creditCardRepository.save(creditCard);
                throw new ResponseStatusException(HttpStatus.OK,"Process is success...");
            }
        }
    }

    public void depositMoneyAtAtm(Long creditCardId, String creditCardPassword, double amount) {

        CreditCard creditCard = creditCardRepository.findByCreditCardId(creditCardId);

        if ((!Objects.equals(creditCard.getCreditCardPassword(), creditCardPassword))){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Password is wrong.");
        } else{
            if (amount==0){
                throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Please enter bigger than 0.");
            }else {
                creditCard.setAmountOfDebt(creditCard.getAmountOfDebt()-amount);
                creditCard.setCreditCardLimit(creditCard.getCreditCardLimit()+amount);
                creditCardRepository.save(creditCard);
                throw new ResponseStatusException(HttpStatus.OK,"Process is success...");

            }
        }
    }


    public void withdrawMoneyFromAtm(Long creditCardId, String creditCardPassword, double amount) {

        CreditCard creditCard = creditCardRepository.findByCreditCardId(creditCardId);

        if (creditCard.getCreditCardPassword() != creditCardPassword) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Password is wrong.");
        } else {

            if (creditCard.getCreditCardLimit() < 0 || creditCard.getCreditCardLimit()<amount) {
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Amount is bigger than card limit.");
            } else {
                creditCard.setCreditCardLimit(creditCard.getCreditCardLimit() - amount);
                creditCardRepository.save(creditCard);
                throw new ResponseStatusException(HttpStatus.OK,"Process is success...");
            }
        }
    }


    public void upgradeLimit(Long creditCardId, double limit) {
        CreditCard creditCard = creditCardRepository.findByCreditCardId(creditCardId);
        if (creditCard.getAmountOfDebt()>creditCard.getCreditCardLimit()){
            throw new ResponseStatusException(HttpStatus.EXPECTATION_FAILED, "Password is wrong.");

        }else {
            creditCard.setCreditCardLimit(creditCard.getCreditCardLimit()+(limit/4));
            creditCardRepository.save(creditCard);
            throw new ResponseStatusException(HttpStatus.OK,"Process is success...");
        }
    }
}
