package org.kodluyoruz.mybank.controller.dto.creditcard;

import lombok.Builder;
import lombok.Data;
import org.kodluyoruz.mybank.controller.dto.account.AccountDto;
import org.kodluyoruz.mybank.model.CreditCard;

@Data
@Builder
public class CreditCardCreateDto {

    private AccountDto account;
    private double creditCardLimit;

    private String creditCardPassword;

    private String creditCcv;


    public CreditCard toCreditCard(){
        return CreditCard.builder()
                .creditCardLimit(this.creditCardLimit)
                .CreditCardPassword(this.creditCardPassword)
                .creditCcv(this.creditCcv)
                .account(this.account.toAccount())
                .build();
    }
}
