package org.kodluyoruz.mybank.controller.dto.customer;

import lombok.Getter;
import lombok.Setter;
import org.kodluyoruz.mybank.model.Customer;

import java.time.LocalDate;

@Setter
@Getter
public class CustomerUpdateDto {


    private String name;
    private String surname;
    private String emailAddress;
    private int customerNumber;
    private LocalDate birthDate;


    public Customer toCustomer(){
        return Customer.builder()
                .name(this.name)
                .surname(this.surname)
                .emailAddress(this.emailAddress)
                .birthDate(this.birthDate)
                .build();
    }

}
