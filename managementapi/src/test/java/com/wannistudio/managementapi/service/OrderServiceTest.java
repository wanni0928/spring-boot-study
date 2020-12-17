package com.wannistudio.managementapi.service;

import com.wannistudio.managementapi.domain.*;
import com.wannistudio.managementapi.repository.OrderRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class OrderServiceTest {
    @Autowired
    EntityManager em;
    @Autowired
    OrderService orderService;
    @Autowired
    OrderRepository orderRepository;

    @Test
    void orderItem() throws Exception {
        //given
        Customer customer = new Customer();
        customer.setCustomerName("wanni");
        customer.setPlace(new Place("gaepo", "seoul", "123", "KOR"));
        em.persist(customer);

        Product product = new Product();
        product.setProductName("catLeaf");
        product.setPrice(10000);
        product.setProductQuantity(2);
        em.persist(product);

        Shipper shipper = new Shipper();
        shipper.setShipperName("wanniho");
        shipper.setPhone("12345");
        em.persist(shipper);

        int orderCnt = 1;
        Long order = orderService.order(customer.getId(), product.getId(), shipper.getId(), 1);

        //when
        assertThat(orderRepository.findOne(order).getCustomer().getCustomerName()).isEqualTo("wanni");
    }
}