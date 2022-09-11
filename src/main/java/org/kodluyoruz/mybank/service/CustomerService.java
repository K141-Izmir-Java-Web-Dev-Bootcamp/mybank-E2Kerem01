package org.kodluyoruz.mybank.service;

import org.kodluyoruz.mybank.model.Account;
import org.kodluyoruz.mybank.model.CreditCard;
import org.kodluyoruz.mybank.model.Customer;
import org.kodluyoruz.mybank.repository.AccountRepository;
import org.kodluyoruz.mybank.repository.CreditCardRepository;
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
    private final CreditCardRepository creditCardRepository;
    private final AccountRepository accountRepository;

    public CustomerService(CustomerRepository customerRepository, CreditCardRepository creditCardRepository, AccountRepository accountRepository) {
        this.customerRepository = customerRepository;
        this.creditCardRepository = creditCardRepository;
        this.accountRepository = accountRepository;
    }

    public Customer create(Customer customer){
        return customerRepository.save(customer);
    }

    public void deleteCustomer(Long id){

        Account account = accountRepository.findByCustomer_CustomerId(id);
        if (account==null){
            customerRepository.deleteById(id);
            throw new ResponseStatusException(HttpStatus.OK,"delete this customer.");
        }
        if (account.getBalance()>0){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"Cant delete this customer.");
        }else {
            CreditCard creditCard = creditCardRepository.findByAccount_AccountId(account.getAccountId());
            if (creditCard==null){
                accountRepository.deleteById(account.getAccountId());
                customerRepository.deleteById(id);
                throw new ResponseStatusException(HttpStatus.OK,"delete this customer.");
            }else {
                if (creditCard.getAmountOfDebt()>0){
                    throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"Cant delete this customer.");
                }else {
                    creditCardRepository.deleteById(creditCard.getCreditCardId());
                    accountRepository.deleteById(account.getAccountId());
                    customerRepository.deleteById(id);
                    throw new ResponseStatusException(HttpStatus.OK,"Process is success...");
                }
            }
        }
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
