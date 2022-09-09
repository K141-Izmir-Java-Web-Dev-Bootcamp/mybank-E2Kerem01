package org.kodluyoruz.mybank.service;

import org.kodluyoruz.mybank.model.Customer;
import org.kodluyoruz.mybank.repository.CustomerRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@Service
public class CustomerService {

    private final CustomerRepository customerRepository;

    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public Customer create(Customer customer){
        return customerRepository.save(customer);
    }

    public void deleteById(Long id){
        customerRepository.deleteById(id);
    }

    public Page<Customer> getPagesOfCustomer(Pageable pageable){
        return customerRepository.findAll(pageable);
    }

    public Customer updateEmail(Long customerId, String email) {
        Customer customer = this.customerRepository.findById(customerId).orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND,"Customer not found"));
        customer.setEmailAddress(email);
        return customerRepository.save(customer);

    }

    public Customer updatePhone(Long customerId, String phone) {
        Customer customer = this.customerRepository.findById(customerId).orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND,"Customer not found"));
        customer.setPhoneNumber(phone);
        return customerRepository.save(customer);

    }

    public Optional<Customer> getCustomer(Long id){
        return customerRepository.findById(id);

    }

}
