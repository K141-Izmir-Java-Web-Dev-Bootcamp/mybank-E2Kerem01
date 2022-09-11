package org.kodluyoruz.mybank.repository;

import org.kodluyoruz.mybank.model.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {

    Account findByIban(UUID iban);
    Account findByAccountId(Long accountId);
    Account findByCustomer_CustomerId(Long customerId);



}
