package org.kodluyoruz.mybank.controller;

import org.kodluyoruz.mybank.controller.dto.transfer.TransferDto;
import org.kodluyoruz.mybank.service.TransferService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/")
public class TransferController {

    private final TransferService transferService;

    public TransferController(TransferService transferService) {
        this.transferService = transferService;
    }

    /*@PostMapping("bytransfer/{accumulation_account}/{current_account}")
    @ResponseStatus(HttpStatus.OK)
    public TransferDto create(@PathVariable String senderIban
            ,@PathVariable String receicerIban, @PathVariable int amount){


    }*/
}
