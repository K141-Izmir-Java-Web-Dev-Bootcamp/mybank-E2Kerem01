package org.kodluyoruz.mybank.controller.dto.bankcard;

import lombok.Builder;
import lombok.Data;
import org.kodluyoruz.mybank.controller.dto.account.AccountDto;
import org.kodluyoruz.mybank.model.BankCard;
@Data
@Builder
public class BankCardCreateDto {


    private AccountDto account;
    private int bankCardLimit;
    private String bankCardPassword;
    private String bankCardCcv;

    public BankCard toBankCard(){
        return BankCard.builder()
                .bankCardLimit(this.bankCardLimit)
                .bankCardPassword(this.bankCardPassword)
                .bankCardCcv(this.bankCardCcv)
                .account(this.account.toAccount())
                .build();
    }
}
