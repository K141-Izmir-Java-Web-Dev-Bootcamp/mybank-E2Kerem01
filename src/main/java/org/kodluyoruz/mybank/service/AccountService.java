package org.kodluyoruz.mybank.service;

import org.kodluyoruz.mybank.model.Account;
import org.kodluyoruz.mybank.repository.AccountRepository;
import org.springframework.stereotype.Service;

@Service
public class AccountService {

    private final AccountRepository accountRepository;

    public AccountService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }


    public Account create(Account account){
        return accountRepository.save(account);
    }
}
