package org.kodluyoruz.mybank.controller.Dto;

import lombok.Setter;
import org.kodluyoruz.mybank.model.Customer;
import java.time.LocalDate;


@Setter
public class CustomerCreatDto {

    private String name;
    private String surname;
    private String emailAddress;
    private LocalDate birthDate;
    private String tckn;
    private String phoneNumber;
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
