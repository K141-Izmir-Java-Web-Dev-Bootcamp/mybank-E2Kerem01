package org.kodluyoruz.mybank.controller.Dto;


import org.kodluyoruz.mybank.model.Account;
import org.kodluyoruz.mybank.model.BankCard;
import org.kodluyoruz.mybank.model.Customer;


public class BankCardCreateDto {

    private Account account;

    private CustomerCreatDto customer;
    private int bankCard_id;
    private int bankCardLimit;
    private String bankCardPassword;
    private String ccv;



    public BankCard tobankCard(){
        return BankCard.builder()
                .bankCard_id(this.bankCard_id)
                .bankCardLimit(this.bankCardLimit)
                .bankCardPassword(this.bankCardPassword)
                .ccv(this.ccv)
                .customer(customer.toCustomer())
                .build();
    }

}
