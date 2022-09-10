package org.kodluyoruz.mybank.controller.dto.transfer;

import lombok.Builder;
import lombok.Data;
import org.kodluyoruz.mybank.model.Transfer;

import java.time.LocalDate;

@Data
@Builder
public class TransferDto {

    private Long transferId;

    private LocalDate transferDate;

    private double transferAmount;


    public Transfer toTransfer(){
        return Transfer.builder()
                .transferId(this.transferId)
                .transferDate(this.transferDate)
                .transferAmount(this.transferAmount)
                .build();

    }

}
