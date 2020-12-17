package com.wannistudio.managementapi;

import com.wannistudio.managementapi.domain.Customer;
import com.wannistudio.managementapi.domain.Place;
import com.wannistudio.managementapi.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
public class AppRunner implements ApplicationRunner {
    @Autowired
    CustomerService customerService;

    @Override
    public void run(ApplicationArguments args) throws Exception {
//        Customer customer = new Customer();
//        customer.setName("wanni");
//        customer.setPlace(new Place("개포동", "서울", "1234", "한국"));
//        customerService.join(customer);
    }
}
