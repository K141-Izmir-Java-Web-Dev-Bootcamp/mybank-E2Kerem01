package org.kodluyoruz.mybank.controller.dto.creditcard;

import lombok.Builder;
import lombok.Data;
import org.kodluyoruz.mybank.controller.dto.account.AccountDto;
import org.kodluyoruz.mybank.model.CreditCard;

@Data
@Builder
public class CreditCardDto {

    private Long creditCardId;

    private double creditCardLimit;
    private double amountOfDebt;
    private String creditCardPassword;

    private String creditCardCvc;

    private AccountDto account;

    public CreditCard toCreditCard(){
        return CreditCard.builder()
                .amountOfDebt(this.amountOfDebt)
                .creditCardId(this.creditCardId)
                .creditCardLimit(this.creditCardLimit)
                .creditCardPassword(this.creditCardPassword)
                .creditCardCvc(this.creditCardCvc)
                .account(this.account.toAccount())
                .build();
    }

}
