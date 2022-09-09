package org.kodluyoruz.mybank.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.kodluyoruz.mybank.controller.dto.creditcard.CreditCardDto;

import javax.persistence.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "credit_card")
public class CreditCard {


    @Id
    @GeneratedValue
    @Column(name = "creditCard_number", nullable = false)
    private Long creditCard_id;

    @Column(name = "creditCard_limit")
    private double creditCardLimit;

    @Column(name = "creditCard_password")
    private String CreditCardPassword;

    @Column(name = "creditCard_ccv")
    private String creditCcv;

    @ManyToOne
    @JoinColumn(name = "creditCard_account_id", referencedColumnName = "accountId")
    private Account account;

    public CreditCardDto toCreditCardDto(){
        return CreditCardDto.builder()
                .creditCard_id(this.creditCard_id)
                .creditCardLimit(this.creditCardLimit)
                .creditCardPassword(this.CreditCardPassword)
                .creditCcv(this.creditCcv)
                .account(this.account.toAccountDto())
                .build();
    }

}
