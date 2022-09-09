package org.kodluyoruz.mybank.repository;

import org.kodluyoruz.mybank.model.BankCard;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface BankCardRepository extends PagingAndSortingRepository<BankCard, Long> {

}
