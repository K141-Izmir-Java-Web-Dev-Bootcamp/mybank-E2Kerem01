package org.kodluyoruz.mybank.service;

import org.kodluyoruz.mybank.model.Account;
import org.kodluyoruz.mybank.repository.AccountRepository;
import org.kodluyoruz.mybank.repository.TransferRepository;
import org.kodluyoruz.mybank.utilities.money.type.MoneyType;
import org.kodluyoruz.mybank.utilities.type.of.account.AccountType;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.UUID;

@Service
public class TransferService {


    private final AccountRepository accountRepository;

    private final TransferRepository transferRepository;

    public TransferService(AccountRepository accountRepository, TransferRepository transferRepository) {
        this.accountRepository = accountRepository;
        this.transferRepository = transferRepository;
    }

    public void transferToIban(double amount, UUID senderIban, UUID receiverIban) {

        Account sendAccount = accountRepository.findByIban(senderIban);
        Account receAccount = accountRepository.findByIban(receiverIban);

        if ((amount > sendAccount.getBalance()) && (senderIban != receiverIban)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Balance is weak");
        } else {
            if (sendAccount.getAccountType() == AccountType.ACCUMULATION_ACCOUNT) {

                if ((sendAccount.getMoneyType() == MoneyType.TRY) && (receAccount.getMoneyType() == MoneyType.TRY)) {
                    sendAccount.setBalance(sendAccount.getBalance() - amount);
                    receAccount.setBalance(receAccount.getBalance() + amount);
                    accountRepository.save(sendAccount);
                    accountRepository.save(receAccount);
                    throw new ResponseStatusException(HttpStatus.OK, "Process is success...");

                } else if ((sendAccount.getMoneyType() == MoneyType.TRY) && (receAccount.getMoneyType() == MoneyType.EUR)) {

                    sendAccount.setBalance(sendAccount.getBalance() - amount);
                    amount = amount / 18.5;
                    receAccount.setBalance(receAccount.getBalance() + amount);
                    accountRepository.save(sendAccount);
                    accountRepository.save(receAccount);
                    throw new ResponseStatusException(HttpStatus.OK, "Process is success...");

                } else if ((sendAccount.getMoneyType() == MoneyType.TRY) && (receAccount.getMoneyType() == MoneyType.USD)) {

                    sendAccount.setBalance(sendAccount.getBalance() - amount);
                    amount = amount / 18;
                    receAccount.setBalance(receAccount.getBalance() + amount);
                    accountRepository.save(sendAccount);
                    accountRepository.save(receAccount);
                    throw new ResponseStatusException(HttpStatus.OK, "Process is success...");

                }

                // Eur

                else if ((sendAccount.getMoneyType() == MoneyType.EUR) && (receAccount.getMoneyType() == MoneyType.TRY)) {

                    sendAccount.setBalance(sendAccount.getBalance() - amount);
                    amount = amount * 18.5;
                    receAccount.setBalance(receAccount.getBalance() + amount);
                    accountRepository.save(sendAccount);
                    accountRepository.save(receAccount);
                    throw new ResponseStatusException(HttpStatus.OK, "Process is success...");

                } else if ((sendAccount.getMoneyType() == MoneyType.EUR) && (receAccount.getMoneyType() == MoneyType.USD)) {

                    sendAccount.setBalance(sendAccount.getBalance() - amount);
                    amount = amount + (amount * 1.05);
                    receAccount.setBalance(receAccount.getBalance() + amount);
                    accountRepository.save(sendAccount);
                    accountRepository.save(receAccount);
                    throw new ResponseStatusException(HttpStatus.OK, "Process is success...");

                } else if ((sendAccount.getMoneyType() == MoneyType.EUR) && (receAccount.getMoneyType() == MoneyType.EUR)) {

                    sendAccount.setBalance(sendAccount.getBalance() - amount);
                    receAccount.setBalance(receAccount.getBalance() + amount);
                    accountRepository.save(sendAccount);
                    accountRepository.save(receAccount);
                    throw new ResponseStatusException(HttpStatus.OK, "Process is success...");




                } else if ((sendAccount.getMoneyType() == MoneyType.USD) && (receAccount.getMoneyType() == MoneyType.TRY)) {

                    sendAccount.setBalance(sendAccount.getBalance() - amount);
                    amount = amount * 18;
                    receAccount.setBalance(receAccount.getBalance() + amount);
                    accountRepository.save(sendAccount);
                    accountRepository.save(receAccount);
                    throw new ResponseStatusException(HttpStatus.OK, "Process is success...");

                } else if ((sendAccount.getMoneyType() == MoneyType.USD) && (receAccount.getMoneyType() == MoneyType.EUR)) {

                    sendAccount.setBalance(sendAccount.getBalance() - amount);
                    amount = amount - (amount * 1.05);
                    receAccount.setBalance(receAccount.getBalance() + amount);
                    accountRepository.save(sendAccount);
                    accountRepository.save(receAccount);
                    throw new ResponseStatusException(HttpStatus.OK, "Process is success...");

                } else if ((sendAccount.getMoneyType() == MoneyType.USD) && (receAccount.getMoneyType() == MoneyType.USD)) {

                    sendAccount.setBalance(sendAccount.getBalance() - amount);
                    receAccount.setBalance(receAccount.getBalance() + amount);
                    accountRepository.save(sendAccount);
                    accountRepository.save(receAccount);
                    throw new ResponseStatusException(HttpStatus.OK, "Process is success...");

                }
                else {
                    throw new ResponseStatusException(HttpStatus.EXPECTATION_FAILED, "Current account dont send money");
                }
            }
        }
    }
}