package org.kodluyoruz.mybank.repository;

import org.kodluyoruz.mybank.model.Transfer;
import org.springframework.data.repository.PagingAndSortingRepository;


public interface TransferRepository extends PagingAndSortingRepository<Transfer, Long> {
}
