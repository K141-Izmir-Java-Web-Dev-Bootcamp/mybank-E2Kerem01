package org.kodluyoruz.mybank.model;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "account")
public class Account {


    @Id
    @GeneratedValue
    private Long account_id;

    @ManyToOne
    @JoinColumn(name = "customer_id",referencedColumnName = "id")
    private Customer customer;

    @OneToOne(mappedBy = "account")
    public BankCard bankCard;


    @Column(name = "account_type")
    private int account_type;

    @Column(unique = true,name = "iban_number")
    private UUID iban;



   /* @OneToOne(mappedBy = "account",cascade = CascadeType.REMOVE)
    private DebitCard debitCard;*/



}
