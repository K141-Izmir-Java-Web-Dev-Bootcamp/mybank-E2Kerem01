package org.kodluyoruz.mybank.controller.dto.creditcard;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.kodluyoruz.mybank.controller.dto.account.AccountDto;
import org.kodluyoruz.mybank.model.CreditCard;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
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
