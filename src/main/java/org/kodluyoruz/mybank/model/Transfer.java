package org.kodluyoruz.mybank.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.kodluyoruz.mybank.controller.dto.transfer.TransferDto;

import javax.persistence.*;
import java.time.LocalDate;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "transfer")
public class Transfer {

    @Id
    @GeneratedValue
    private Long transferId;

    private LocalDate transferDate;

    private double transferAmount;

    @ManyToOne
    @JoinColumn(name = "transfer_account_id",referencedColumnName = "accountId")
    private Account account;

    public TransferDto toTransferDto(){
        return TransferDto.builder()
                .transferId(this.transferId)
                .transferDate(this.transferDate)
                .transferAmount(this.transferAmount)
                .build();
    }

}
