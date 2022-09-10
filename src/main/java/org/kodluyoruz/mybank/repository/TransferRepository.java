package org.kodluyoruz.mybank.repository;

import org.kodluyoruz.mybank.model.Account;
import org.kodluyoruz.mybank.model.Transfer;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface TransferRepository extends PagingAndSortingRepository<Transfer, Long> {


}
