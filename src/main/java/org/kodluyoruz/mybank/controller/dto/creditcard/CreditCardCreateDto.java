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

    private String creditCardCvc;


    public CreditCard toCreditCard(){
        return CreditCard.builder()
                .creditCardLimit(this.creditCardLimit)
                .creditCardPassword(this.creditCardPassword)
                .creditCardCvc(this.creditCardCvc)
                .account(this.account.toAccount())
                .build();
    }
}
