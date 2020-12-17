package com.wannistudio.managementapi.service;

import com.wannistudio.managementapi.domain.*;
import com.wannistudio.managementapi.repository.CustomerRepository;
import com.wannistudio.managementapi.repository.OrderRepository;
import com.wannistudio.managementapi.repository.ProductRepository;
import com.wannistudio.managementapi.repository.ShipperRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class OrderService {
    private final ProductRepository productRepository;
    private final CustomerRepository customerRepository;
    private final OrderRepository orderRepository;
    private final ShipperRepository shipperRepository;

    @Transactional
    public Long order(Long customerId, Long productId, Long shipperId, int count) {
        // entity
        Customer customer = customerRepository.findOne(customerId);
        Product product = productRepository.findOne(productId);
        Shipper shipper = shipperRepository.findOne(shipperId);

        // orderDetail
        OrderDetail orderDetail = OrderDetail.createOrderDetail(product, count);

        // order
        Order order = Order.createOrder(customer, shipper, orderDetail);

        //save
        orderRepository.save(order);

        return order.getId();
    }

    @Transactional
    public void cancelOrder(Long orderId){
        Order order= orderRepository.findOne(orderId);
        order.cancel();
    }
}
