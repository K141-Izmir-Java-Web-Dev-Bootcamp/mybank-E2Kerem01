package org.kodluyoruz.mybank.repository;

import org.kodluyoruz.mybank.model.Customer;
import org.springframework.data.repository.PagingAndSortingRepository;


public interface CustomerRepository extends PagingAndSortingRepository<Customer, Long> {


}
