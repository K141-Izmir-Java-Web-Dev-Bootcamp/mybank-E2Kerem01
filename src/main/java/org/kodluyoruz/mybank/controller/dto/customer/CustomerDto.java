package org.kodluyoruz.mybank.controller.dto.customer;

import lombok.Builder;
import lombok.Data;
import org.kodluyoruz.mybank.model.Customer;

import java.time.LocalDate;

@Data
@Builder
public class CustomerDto {


    private Long customerId;
    private String name;
    private String surname;
    private String emailAddress;
    private String tckn;
    private String phoneNumber;
    private LocalDate birthDate;
    private LocalDate membershipDate;

    public Customer toCustomer() {
        return Customer.builder()
                .customerId(this.customerId)
                .tckn(this.tckn)
                .name(this.name)
                .emailAddress(this.emailAddress)
                .birthDate(this.birthDate)
                .membershipDate(this.membershipDate)
                .surname(this.surname)
                .phoneNumber(this.phoneNumber)
                .build();
    }


}
