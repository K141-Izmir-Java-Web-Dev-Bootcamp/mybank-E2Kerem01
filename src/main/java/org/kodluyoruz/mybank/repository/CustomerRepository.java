package org.kodluyoruz.mybank.repository;

import org.kodluyoruz.mybank.model.Customer;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.Optional;


public interface CustomerRepository extends PagingAndSortingRepository<Customer, Long> {


}
