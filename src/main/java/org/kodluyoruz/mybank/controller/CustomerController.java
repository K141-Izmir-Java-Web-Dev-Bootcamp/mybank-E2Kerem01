package org.kodluyoruz.mybank.controller;

import org.kodluyoruz.mybank.controller.Dto.CustomerCreatDto;
import org.kodluyoruz.mybank.controller.Dto.CustomerDto;
import org.kodluyoruz.mybank.controller.Dto.CustomerUpdateDto;
import org.kodluyoruz.mybank.model.Customer;
import org.kodluyoruz.mybank.service.CustomerService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/v1/")
public class CustomerController {

    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @PostMapping("customer")
    public CustomerDto creat(@RequestBody CustomerCreatDto dto){
        Customer customer = customerService.creat(dto.toCustomer());
        return CustomerDto.builder()
                .id(customer.getId())
                .name(customer.getName())
                .surname(customer.getSurname())
                .emailAddress(customer.getEmailAddress())
                .birthDate(customer.getBirthDate())

                .membershipDate(customer.getMembershipDate())
                .build();
    }

    @DeleteMapping("{id}")
    public void deleteCustomer(@PathVariable Long id){
        customerService.deleteById(id);

    }

    @GetMapping("customers")
    public Page<CustomerDto> listCustomers(Pageable pageable){
        return customerService.getPagesOfCustomer(pageable)
                .map(customer -> CustomerDto.builder()
                        .id(customer.getId())
                        .name(customer.getName())
                        .surname(customer.getSurname())
                        .emailAddress(customer.getEmailAddress())
                        .birthDate(customer.getBirthDate())
                        .membershipDate(customer.getMembershipDate())
                        .build());
    }



    @PutMapping("{id}")
    public CustomerDto update(@RequestBody CustomerUpdateDto dto,@PathVariable Long id){
        Customer customer = customerService.uptade(dto.toCustomer());
        return CustomerDto.builder()
                .id(customer.getId())
                .name(customer.getName())
                .surname(customer.getSurname())
                .emailAddress(customer.getEmailAddress())
                .birthDate(customer.getBirthDate())
                .build();
    }

    @GetMapping("customer/{id}")
    public Optional<CustomerDto> getCustomerById(@PathVariable Long id){
        return customerService.getCustomer(id)
                .map(customer -> CustomerDto.builder()
                        .id(customer.getId())
                        .name(customer.getName())
                        .surname(customer.getSurname())
                        .emailAddress(customer.getEmailAddress())
                        .birthDate(customer.getBirthDate())
                        .membershipDate(customer.getMembershipDate())
                        .build());

    }

    /*@PutMapping("customer")
    public CustomerDto update(@RequestBody CustomerUpdateDto dto){
        Optional<Customer> customerOptional = customerService.update(dto.getId());
        return CustomerDto.builder()
                .id(customerOptional.getId())
                .name(customerOptional.getName())
                .surname(customerOptional.getSurname())
                .emailAddress(customerOptional.getEmailAddress())
                .birthDate(customerOptional.getBirthDate())
                .customerNumber(customerOptional.getCustomerNumber())
                .membershipDate(customerOptional.getMembershipDate())
                .build();
    }*/
}
