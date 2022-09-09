package org.kodluyoruz.mybank.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.kodluyoruz.mybank.controller.dto.customer.CustomerDto;

import java.util.Set;

import javax.persistence.*;
import java.time.LocalDate;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "customer")
public class Customer {

    @Id
    @GeneratedValue
    private Long customerId;

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

    @Column(name = "phone_number")
    private String phoneNumber;

    @OneToMany(mappedBy = "customer")
    private Set<Account> accounts;

    public CustomerDto toCustomerDto() {
        return CustomerDto.builder()
                .customerId(this.customerId)
                .tckn(this.tckn)
                .surname(this.surname)
                .emailAddress(this.emailAddress)
                .name(this.name)
                .phoneNumber(this.phoneNumber)
                .birthDate(this.birthDate)
                .membershipDate(this.membershipDate)
                .build();
    }



}
