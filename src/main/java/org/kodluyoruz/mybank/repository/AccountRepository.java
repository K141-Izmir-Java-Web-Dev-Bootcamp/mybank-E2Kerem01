package org.kodluyoruz.mybank.repository;

import org.kodluyoruz.mybank.model.Account;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface AccountRepository extends PagingAndSortingRepository<Account, Long> {
}
