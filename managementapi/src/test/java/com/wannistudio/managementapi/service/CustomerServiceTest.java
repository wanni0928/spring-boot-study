package com.wannistudio.managementapi.service;

import com.wannistudio.managementapi.domain.Customer;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class CustomerServiceTest {
    @Autowired CustomerService customerService;

    @Test
    void save() throws Exception {
        //given
        Customer customer = new Customer();
        customer.setCustomerName("wanni");

        //when
        Long join = customerService.join(customer);

        //then
        assertThat(customer).isSameAs(customerService.findOne(join));
    }

    @Test
    void checkDuplicate() throws Exception {
        //given
        Customer customer1 = new Customer();
        customer1.setCustomerName("wanni");
        Customer customer2 = new Customer();
        customer2.setCustomerName("wanni");

        //when
        customerService.join(customer1);

        //then
        assertThrows(IllegalStateException.class, () -> customerService.join(customer2));
    }
}