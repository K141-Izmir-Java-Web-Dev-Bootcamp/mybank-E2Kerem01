package org.kodluyoruz.mybank.controller.dto.creditcard;

import lombok.Builder;
import lombok.Data;
import org.kodluyoruz.mybank.controller.dto.account.AccountDto;
import org.kodluyoruz.mybank.model.CreditCard;

@Data
@Builder
public class CreditCardDto {

    private Long creditCard_id;

    private double creditCardLimit;

    private String creditCardPassword;

    private String creditCcv;

    private AccountDto account;

    public CreditCard toCreditCard(){
        return CreditCard.builder()
                .creditCard_id(this.creditCard_id)
                .creditCardLimit(this.creditCardLimit)
                .CreditCardPassword(this.creditCardPassword)
                .creditCcv(this.creditCcv)
                .account(this.account.toAccount())
                .build();
    }

}
