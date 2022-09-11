package org.kodluyoruz.mybank.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.kodluyoruz.mybank.controller.dto.creditcard.CreditCardDto;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

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
    private Long creditCardId;

    @Column(name = "creditCard_limit")
    private double creditCardLimit;

    @Column(name = "debt")
    private double amountOfDebt;

    @Column(name = "creditCard_password")
    @NotBlank
    private String creditCardPassword;

    @Column(name = "creditCard_cvc")
    @NotBlank
    private String creditCardCvc;

    @ManyToOne
    @JoinColumn(name = "creditCard_account_id", referencedColumnName = "accountId")
    private Account account;

    public CreditCardDto toCreditCardDto(){
        return CreditCardDto.builder()
                .amountOfDebt(this.amountOfDebt)
                .creditCardId(this.creditCardId)
                .creditCardLimit(this.creditCardLimit)
                .creditCardPassword(this.creditCardPassword)
                .creditCardCvc(this.creditCardCvc)
                .account(this.account.toAccountDto())
                .build();
    }

}
