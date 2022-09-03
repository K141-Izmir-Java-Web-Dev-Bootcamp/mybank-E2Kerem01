package org.kodluyoruz.mybank.controller.Dto;

import lombok.Setter;
import org.kodluyoruz.mybank.model.Customer;
import java.time.LocalDate;


@Setter
public class CustomerCreatDto {

    private String name;
    private String surname;
    private String emailAddress;
    private int customerNumber;
    private LocalDate birthDate;
    private LocalDate membershipDate;


    public Customer toCustomer(){
        return Customer.builder()
                .name(this.name)
                .surname(this.surname)
                .birthDate(this.birthDate)
                .emailAddress(this.emailAddress)
                .customerNumber(this.customerNumber)
                .membershipDate(this.membershipDate)
                .build();
    }

}
