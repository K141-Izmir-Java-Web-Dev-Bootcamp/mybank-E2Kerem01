package org.kodluyoruz.mybank.service;

import org.kodluyoruz.mybank.model.Account;
import org.kodluyoruz.mybank.repository.AccountRepository;
import org.kodluyoruz.mybank.repository.TransferRepository;
import org.kodluyoruz.mybank.utilities.money.type.MoneyType;
import org.kodluyoruz.mybank.utilities.type.of.account.AccountType;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Objects;
import java.util.UUID;

@Service
public class TransferService {


    private final AccountRepository accountRepository;

    private final TransferRepository transferRepository;

    public TransferService(AccountRepository accountRepository, TransferRepository transferRepository) {
        this.accountRepository = accountRepository;
        this.transferRepository = transferRepository;
    }

    public void transferToIban(double amount, UUID senderIban, UUID receiverIban){

        Account sendAccount = accountRepository.findByIban(senderIban);
        Account receAccount = accountRepository.findByIban(receiverIban);

        if ((amount > sendAccount.getBalance())&&(senderIban!=receiverIban)){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Balance is weak");
        }else {
            if(sendAccount.getAccountType()==AccountType.ACCUMULATION_ACCOUNT){
                sendAccount.setBalance(sendAccount.getBalance()-amount);
                receAccount.setBalance(receAccount.getBalance()+amount);
                accountRepository.save(sendAccount);
                accountRepository.save(receAccount);
                throw new ResponseStatusException(HttpStatus.OK,"Process is success...");
            }else {
                throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Current account dont send money");
            }
        }


    }

    /*public void transferBetweenMyAccounts(Long account1, Long account2, double amount) {

        Account sendAccount = accountRepository.findByAccountId(account1);
        Account receAccount = accountRepository.findByAccountId(account2);


        if ((sendAccount.getBalance()<0)&&(sendAccount.getBalance()>amount)){

            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"Balance is weak");
        }
            else {

                sendAccount.setBalance(sendAccount.getBalance()-amount);
                receAccount.setBalance(receAccount.getBalance()+amount);
                accountRepository.save(sendAccount);
                accountRepository.save(receAccount);

            }
        }*/



}
