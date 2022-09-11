package org.kodluyoruz.mybank.repository;

import org.kodluyoruz.mybank.model.CreditCard;
import org.kodluyoruz.mybank.model.Customer;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends PagingAndSortingRepository<Customer, Long> {


}
