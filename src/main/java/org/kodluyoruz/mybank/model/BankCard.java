package org.kodluyoruz.mybank.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.kodluyoruz.mybank.controller.dto.bankcard.BankCardDto;

import javax.persistence.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "bank_card")
public class BankCard {

    @Id
    @GeneratedValue
    @Column(name = "bankcard_number", nullable = false)
    private Long bankCardId;

    @Column(name = "bankCard_limit")
    private double bankCardLimit;

    @Column(name = "bankCard_password")
    private String bankCardPassword;
    @Column(name = "bankCard_Ccv")
    private String bankCardCvc;

    @OneToOne
    @JoinColumn(name = "bankcard_account_id", referencedColumnName = "accountId")
    private Account account;

    private BankCardDto toBankCardDto(){
        return  BankCardDto.builder()
                .bankCardId(this.bankCardId)
                .bankCardLimit(this.bankCardLimit)
                .bankCardPassword(this.bankCardPassword)
                .bankCardCvc(this.bankCardCvc)
                .build();

    }

}
