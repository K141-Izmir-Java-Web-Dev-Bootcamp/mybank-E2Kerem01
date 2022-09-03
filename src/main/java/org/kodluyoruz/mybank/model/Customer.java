package org.kodluyoruz.mybank.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Customer {

    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false, length = 100)
    private String name;

    @Column(name = "surname")
    private String surname;

    @Column(name = "email_address")
    private String emailAddress;


    @Column(name = "customer_Number", unique = true)
    private int customerNumber;

    @Column(name = "birth_Date")
    private LocalDate birthDate;

    @Column(name = "membership_Date")
    private LocalDate membershipDate;

    @OneToMany(mappedBy = "customer")
    private List<Account> accounts;

}
