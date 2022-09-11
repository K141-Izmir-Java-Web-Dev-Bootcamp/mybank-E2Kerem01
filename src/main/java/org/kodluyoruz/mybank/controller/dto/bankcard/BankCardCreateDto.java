package org.kodluyoruz.mybank.controller.dto.bankcard;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.kodluyoruz.mybank.controller.dto.account.AccountDto;
import org.kodluyoruz.mybank.model.BankCard;
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BankCardCreateDto {


    private AccountDto account;
    private int bankCardLimit;
    private String bankCardPassword;
    private String bankCardCvc;

    public BankCard toBankCard(){
        return BankCard.builder()
                .bankCardLimit(this.bankCardLimit)
                .bankCardPassword(this.bankCardPassword)
                .bankCardCvc(this.bankCardCvc)
                .account(this.account.toAccount())
                .build();
    }
}
