package org.kodluyoruz.mybank.model;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.kodluyoruz.mybank.controller.dto.account.AccountDto;
import org.kodluyoruz.mybank.utilities.money.type.MoneyType;
import org.kodluyoruz.mybank.utilities.type.of.account.AccountType;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.Set;
import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "account")
public class Account {


    @Id
    @GeneratedValue
    private Long accountId;

    @Column(name = "amount_of_money")
    @NotBlank
    private double balance;

    @Enumerated(value = EnumType.STRING)
    private AccountType accountType;

    @Enumerated(value =EnumType.STRING)
    private MoneyType moneyType;

    @Column(unique = true,name = "iban_number")
    private UUID iban;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "customers_id",referencedColumnName = "customerId")
    private Customer customer;

    @OneToOne(mappedBy = "account")
    public BankCard bankCard;

    @OneToMany(mappedBy = "account")
    public Set<CreditCard> creditCard;

    @OneToMany(mappedBy = "account")
    public Set<Transfer> transfers;

    public AccountDto toAccountDto() {
        return AccountDto.builder()
                .accountId(this.accountId)
                .iban(this.iban)
                .balance(this.balance)
                .accountType(this.accountType)
                .moneyType(this.moneyType)
                .build();
    }




}
