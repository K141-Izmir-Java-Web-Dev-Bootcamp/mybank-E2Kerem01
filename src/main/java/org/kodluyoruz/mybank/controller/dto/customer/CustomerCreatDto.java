package org.kodluyoruz.mybank.controller.dto.customer;

import lombok.Builder;
import lombok.Data;
import org.kodluyoruz.mybank.model.Customer;

import java.time.LocalDate;

@Data
@Builder
public class CustomerCreatDto {


    private String name;
    private String surname;
    private String emailAddress;
    private String tckn;
    private String phoneNumber;
    private LocalDate birthDate;
    private LocalDate membershipDate;

    public Customer toCustomer(){
        return Customer.builder()
                .name(this.name)
                .surname(this.surname)
                .birthDate(this.birthDate)
                .emailAddress(this.emailAddress)
                .phoneNumber(this.phoneNumber)
                .tckn(this.tckn)
                .membershipDate(this.membershipDate)
                .build();
    }


}
