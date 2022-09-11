package org.kodluyoruz.mybank.model;

import org.junit.jupiter.api.*;
import org.junit.runner.RunWith;
import org.kodluyoruz.mybank.controller.dto.customer.CustomerCreatDto;
import org.kodluyoruz.mybank.service.CustomerService;
import org.springframework.boot.test.context.SpringBootTest;
import org.junit.jupiter.api.Test;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestPropertySource({ "classpath:application.properties" })
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class CustomerTest {

    private final CustomerService customerService;

    public CustomerTest(CustomerService customerService) {
        this.customerService = customerService;
    }

    @Test
    public void create(){
        Customer customer = CustomerCreatDto.builder()
                .tckn("1234")
                .emailAddress("k@gmail.com")
                .name("Kerem")
                .surname("Metin")
                .phoneNumber("654")
                .build().toCustomer();
        assertNotNull(this.customerService.create(customer));
    }
}
