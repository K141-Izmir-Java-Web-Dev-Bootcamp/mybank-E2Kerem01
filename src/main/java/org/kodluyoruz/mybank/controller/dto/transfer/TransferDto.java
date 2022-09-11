package org.kodluyoruz.mybank.controller.dto.transfer;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.kodluyoruz.mybank.model.Transfer;

import java.time.LocalDate;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
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
