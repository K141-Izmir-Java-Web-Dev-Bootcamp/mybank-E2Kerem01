package org.kodluyoruz.mybank.model;


import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.kodluyoruz.mybank.controller.CustomerController;
import org.kodluyoruz.mybank.controller.dto.customer.CustomerCreatDto;
import org.kodluyoruz.mybank.service.CustomerService;
import org.springframework.test.context.junit4.SpringRunner;
import java.time.LocalDate;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(SpringRunner.class)
public class CustomerTest {

    private CustomerController customerController;
    private CustomerService customerService;

    private final CustomerCreatDto customerCreatDto = new CustomerCreatDto("Kerem","Metin"
            ,"k@gmail.com","1234","1223", LocalDate.now(),LocalDate.now());

    @Test
    public void create(){
        customerService.create(customerCreatDto.toCustomer());
        Assertions.assertAll("Customer",
                ()-> assertEquals(customerCreatDto.getName(),customerController.create(customerCreatDto).getName()),
                ()-> assertEquals(customerCreatDto.getSurname(),customerController.create(customerCreatDto).getSurname()),
                ()-> assertEquals(customerCreatDto.getEmailAddress(),customerController.create(customerCreatDto).getEmailAddress()),
                ()-> assertEquals(customerCreatDto.getTckn(),customerController.create(customerCreatDto).getTckn()),
                ()-> assertEquals(customerCreatDto.getPhoneNumber(),customerController.create(customerCreatDto).getPhoneNumber()),
                ()-> assertEquals(customerCreatDto.getBirthDate(),customerController.create(customerCreatDto).getBirthDate()),
                ()-> assertEquals(customerCreatDto.getMembershipDate(),customerController.create(customerCreatDto).getMembershipDate())
        );
        assertNotNull(customerController.create(customerCreatDto).getName());
        assertNotNull(customerController.create(customerCreatDto).getSurname());
        assertNotNull(customerController.create(customerCreatDto).getPhoneNumber());
        assertNotNull(customerController.create(customerCreatDto).getEmailAddress());
        assertNotNull(customerController.create(customerCreatDto).getTckn());
    }
}
