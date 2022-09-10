package org.kodluyoruz.mybank.controller.dto.customer;

import lombok.Builder;
import lombok.Data;
import org.kodluyoruz.mybank.model.Customer;

import javax.validation.constraints.NotBlank;
import java.time.LocalDate;

@Data
@Builder
public class CustomerCreatDto {

    @NotBlank(message = "This area must not be empty")
    private String name;
    @NotBlank(message = "This area must not be empty")
    private String surname;
    @NotBlank(message = "This area must not be empty")
    private String emailAddress;
    @NotBlank(message = "This area must not be empty")
    private String tckn;
    @NotBlank(message = "This area must not be empty")
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
