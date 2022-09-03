package org.kodluyoruz.mybank.controller.Dto;

import lombok.Builder;
import lombok.Data;
import java.time.LocalDate;

@Data
@Builder
public class CustomerDto {


    private Long id;
    private String name;
    private String surname;
    private String emailAddress;
    private String tckn;
    private String phoneNumber;
    private LocalDate birthDate;
    private LocalDate membershipDate;

}
