package org.kodluyoruz.mybank.controller.Dto;

import lombok.Builder;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.time.LocalDate;

@Data
@Builder
public class CustomerDto {


    private Long id;
    private String name;
    private String surname;
    private String emailAddress;
    private int customerNumber;
    private LocalDate birthDate;
    private LocalDate membershipDate;

}
