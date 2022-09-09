package org.kodluyoruz.mybank.controller.dto.account;

import lombok.Builder;
import lombok.Data;
import org.kodluyoruz.mybank.controller.dto.customer.CustomerDto;
import org.kodluyoruz.mybank.model.Account;
import org.kodluyoruz.mybank.model.BankCard;
import org.kodluyoruz.mybank.model.CreditCard;
import org.kodluyoruz.mybank.model.Customer;
import org.kodluyoruz.mybank.utilities.money.type.MoneyType;
import org.kodluyoruz.mybank.utilities.type.of.account.AccountType;

import java.util.UUID;

@Data
@Builder
public class AccountCreateDto {


    private CustomerDto customer;
    private UUID iban;
    private int balance;
    private AccountType accountType;
    private MoneyType moneyType;


    public Account toAccount() {
        return Account.builder()
                .iban(this.iban)
                .balance(this.balance)
                .accountType(this.accountType)
                .moneyType(this.moneyType)
                .customer(this.customer.toCustomer())
                .build();
    }
}
