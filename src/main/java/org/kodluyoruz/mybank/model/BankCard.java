package org.kodluyoruz.mybank.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "bank_card")
public class BankCard {

    @Id
    @GeneratedValue
    private int bankCard_id;

    @OneToOne
    @JoinColumn(name = "bankcard_customer_id", referencedColumnName = "id")
    private Customer customer;


    @OneToOne
    @JoinColumn(name = "bankcard_account_id", referencedColumnName = "account_id")
    private Account account;

    private int bankCardLimit;

    private String bankCardPassword;

    private String ccv;

}
