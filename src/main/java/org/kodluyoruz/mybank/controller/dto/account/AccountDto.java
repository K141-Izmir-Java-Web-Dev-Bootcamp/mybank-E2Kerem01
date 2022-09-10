package org.kodluyoruz.mybank.controller.dto.account;

import lombok.*;
import org.kodluyoruz.mybank.model.Account;
import org.kodluyoruz.mybank.utilities.money.type.MoneyType;
import org.kodluyoruz.mybank.utilities.type.of.account.AccountType;

import java.util.UUID;

@Setter
@Getter
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AccountDto {

    private Long accountId;
    private double balance;
    private UUID iban;
    private AccountType accountType;
    private MoneyType moneyType;

    public Account toAccount() {
        return Account.builder()
                .accountId(this.accountId)
                .iban(this.iban)
                .accountType(this.accountType)
                .moneyType(this.moneyType)
                .balance(this.balance)
                .build();
    }



}
