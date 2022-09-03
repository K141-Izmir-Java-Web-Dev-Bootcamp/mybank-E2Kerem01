package org.kodluyoruz.mybank.model;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Account {


    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne
    @JoinColumn(name = "customer_id",referencedColumnName = "customer_Number")
    private Customer customer;


    @Column(name = "account_type")
    private int account_type;

    private String a



}
