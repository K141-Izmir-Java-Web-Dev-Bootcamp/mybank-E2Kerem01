package org.kodluyoruz.mybank.service;

import org.kodluyoruz.mybank.model.Customer;
import org.kodluyoruz.mybank.repository.CustomerRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CustomerService {

    private final CustomerRepository customerRepository;

    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public Customer creat(Customer customer){
        return customerRepository.save(customer);
    }

    public void deleteById(Long id){
        customerRepository.deleteById(id);
    }

    public Page<Customer> getPagesOfCustomer(Pageable pageable){
        return customerRepository.findAll(pageable);
    }
    public Customer uptade(Customer customer) {
        return customerRepository.save(customer);
    }

  /*  public Optional<Customer> update(Long customer_id){
        return customerRepository.findById(customer_id);
    }*/
}
