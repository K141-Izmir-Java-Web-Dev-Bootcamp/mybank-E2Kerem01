package org.kodluyoruz.mybank.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.Set;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "customer")
public class Customer {

    @Id
    @GeneratedValue
    private Long id;

    @Column(unique = true,name = "tc_no")
    private String tckn;

    @Column(nullable = false, length = 100)
    private String name;

    @Column(name = "surname")
    private String surname;

    @Column(name = "email_address")
    private String emailAddress;


    @Column(name = "birth_Date")
    private LocalDate birthDate;

    @Column(name = "membership_Date")
    private LocalDate membershipDate;

    @OneToMany(mappedBy = "customer")
    private Set<Account> accounts;

    @Column(unique = true)
    private String phoneNumber;

    @OneToOne(mappedBy = "customer")
    private BankCard bankCard;

    /*@JsonIgnore
    @OneToMany(mappedBy = "customer",cascade = CascadeType.ALL)
    private Set<CreditCard> creditCards;*/

}
