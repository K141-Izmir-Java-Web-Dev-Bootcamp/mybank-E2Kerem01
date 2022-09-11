package org.kodluyoruz.mybank.controller;

import org.kodluyoruz.mybank.controller.dto.customer.CustomerCreatDto;
import org.kodluyoruz.mybank.controller.dto.customer.CustomerDto;
import org.kodluyoruz.mybank.controller.dto.customer.CustomerUpdateDto;
import org.kodluyoruz.mybank.model.Customer;
import org.kodluyoruz.mybank.service.CustomerService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.logging.Logger;

@RestController
@RequestMapping("/api/v1/")
public class CustomerController {

    static Logger logger = Logger.getLogger(CustomerController.class.getName());
    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @PostMapping("customer")
    @ResponseStatus(HttpStatus.CREATED)
    public CustomerDto create(@RequestBody CustomerCreatDto customerCreatDto){
        Customer customer = customerService.create(customerCreatDto.toCustomer());
        return CustomerDto.builder()
                .customerId(customer.getCustomerId())
                .name(customer.getName())
                .tckn(customer.getTckn())
                .phoneNumber(customer.getPhoneNumber())
                .surname(customer.getSurname())
                .emailAddress(customer.getEmailAddress())
                .birthDate(customer.getBirthDate())
                .membershipDate(customer.getMembershipDate())
                .build();
    }

    @DeleteMapping("customer/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteCustomer(@PathVariable Long id){
        customerService.deleteCustomer(id);

    }

    @GetMapping("customers")
    public Page<CustomerDto> listCustomers(Pageable pageable){
        return customerService.getPagesOfCustomer(pageable)
                .map(customer -> CustomerDto.builder()
                        .customerId(customer.getCustomerId())
                        .name(customer.getName())
                        .surname(customer.getSurname())
                        .phoneNumber(customer.getPhoneNumber())
                        .tckn(customer.getTckn())
                        .emailAddress(customer.getEmailAddress())
                        .birthDate(customer.getBirthDate())
                        .membershipDate(customer.getMembershipDate())
                        .build());
    }



    @PutMapping("email/{id}")
    @ResponseStatus(HttpStatus.OK)
    public CustomerDto updateEmail(@RequestBody CustomerUpdateDto dto,@PathVariable Long id, String emailAddress){
        Customer customer = customerService.updateEmail(id,emailAddress);
        return customer.toCustomerDto();
    }

    @PutMapping("phone/{id}")
    @ResponseStatus(HttpStatus.OK)
    public CustomerDto updatePhone(@RequestBody CustomerUpdateDto dto,@PathVariable Long id, String phoneNumber){
        Customer customer = customerService.updatePhone(id,phoneNumber);
        return customer.toCustomerDto();
    }


    @GetMapping("customer/{id}")
    @ResponseStatus(HttpStatus.FOUND)
    public Optional<CustomerDto> getCustomerById(@PathVariable Long id){
        return customerService.getCustomer(id)
                .map(customer -> CustomerDto.builder()
                        .customerId(customer.getCustomerId())
                        .name(customer.getName())
                        .surname(customer.getSurname())
                        .tckn(customer.getTckn())
                        .phoneNumber(customer.getPhoneNumber())
                        .emailAddress(customer.getEmailAddress())
                        .birthDate(customer.getBirthDate())
                        .membershipDate(customer.getMembershipDate())
                        .build());

    }

}
