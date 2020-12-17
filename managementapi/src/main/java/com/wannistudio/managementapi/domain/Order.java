package com.wannistudio.managementapi.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Entity
@Getter @Setter
@Table(name = "orders")
public class Order {
    @Id @GeneratedValue
    @Column(name = "order_id")
    private Long id;
    private LocalDateTime orderDate;
    private OrderStatus orderStatus; // PROCEED, WAITING, CANCEL

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_id")
    private Customer customer;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "employee_id")
    private Employee employee;

    @ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    @JoinColumn(name = "shipper_id")
    private Shipper shipper;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
    private List<OrderDetail> orderDetails = new ArrayList<>();

    public void setCustomer(Customer customer) {
        this.customer = customer;
        customer.getOrders().add(this);
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
        employee.getOrders().add(this);
    }

    public void addOrderDetail(OrderDetail orderDetail) {
        orderDetails.add(orderDetail);
        orderDetail.setOrder(this);
    }

    public static Order createOrder(Customer customer, Shipper shipper, OrderDetail orderDetail) {
        Order order = new Order();
        order.setCustomer(customer);
        order.setShipper(shipper);
//        order.setEmployee(employee);
        order.setOrderStatus(OrderStatus.READY);
        order.addOrderDetail(orderDetail);
        return order;
    }

    public void cancel() {
        if(getOrderStatus() == OrderStatus.CANCEL) {
            throw new IllegalStateException("이미 취소된 상품입니다.");
        }

        this.setOrderStatus(OrderStatus.CANCEL);
        orderDetails.forEach(OrderDetail::cancel);
    }
}
