package org.kodluyoruz.mybank.controller.Dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class BankCardDto {



    private int bankCard_id;
    private int bankCardLimit;
    private String bankCardPassword;
    private String ccv;

}