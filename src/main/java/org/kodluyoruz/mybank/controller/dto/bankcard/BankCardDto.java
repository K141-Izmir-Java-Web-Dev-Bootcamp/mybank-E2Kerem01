package org.kodluyoruz.mybank.controller.dto.bankcard;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.kodluyoruz.mybank.model.BankCard;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BankCardDto {



    private Long bankCardId;
    private double bankCardLimit;
    private String bankCardPassword;
    private String bankCardCvc;

    public BankCard toBankCard(){
        return BankCard.builder()
                .bankCardId(this.bankCardId)
                .bankCardLimit(this.bankCardLimit)
                .bankCardPassword(this.bankCardPassword)
                .bankCardCvc(this.bankCardCvc)
                .build();
    }

}