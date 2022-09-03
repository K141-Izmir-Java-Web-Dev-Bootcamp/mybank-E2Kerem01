package org.kodluyoruz.mybank.controller.Dto;

import lombok.Builder;
import lombok.Data;
import lombok.Setter;

@Setter
@Data
@Builder
public class AccountDto {

    private Long id;
    private int customer_id;

}
