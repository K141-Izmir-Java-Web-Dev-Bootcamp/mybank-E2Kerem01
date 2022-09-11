package org.kodluyoruz.mybank.service;

import org.kodluyoruz.mybank.model.Account;
import org.kodluyoruz.mybank.repository.AccountRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;
import java.util.UUID;
import java.util.logging.Logger;

@Service
public class AccountService {

    private static Logger logger = Logger.getLogger(AccountService.class.getName());
    private final AccountRepository accountRepository;

    public AccountService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    public Account create(Account account){
        return accountRepository.save(account);
    }

    public void deleteAccount(Long id){
        accountRepository.deleteById(id);
    }

    public Page<Account> getPagesOfAccount(Pageable pageable) {
        return accountRepository.findAll(pageable);
    }

    public Optional<Account> getAccount(Long id) {
        return accountRepository.findById(id);
    }

    public void withdrawMoneyWithQrCode(Long accountId, double amount) {
        Boolean qr = true;
        Account account = accountRepository.findByAccountId(accountId);
        if (qr == Boolean.FALSE){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Password is wrong.");
        }else {
            account.setBalance(account.getBalance()-amount);
            accountRepository.save(account);
        }

    }

    /*public void transferToIban(double amount, UUID senderIban, UUID receiverIban){

        Account sendAccount = accountRepository.findByIban(senderIban);
        Account receAccount = accountRepository.findByIban(receiverIban);

        if (amount > sendAccount.getBalance()){
           throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Balance is weak");
        }
            sendAccount.setBalance(sendAccount.getBalance()-amount);
            receAccount.setBalance(receAccount.getBalance()+amount);
            accountRepository.save(sendAccount);
            accountRepository.save(receAccount);


    }*/

}

