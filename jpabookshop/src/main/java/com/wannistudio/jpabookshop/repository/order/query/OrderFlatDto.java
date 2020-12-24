package com.wannistudio.jpabookshop.repository.order.query;

import com.wannistudio.jpabookshop.domain.Address;
import com.wannistudio.jpabookshop.domain.OrderStatus;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class OrderFlatDto { // order 랑 orderItem 조인, orderItem item이랑 조인해서 아예 한번에 쿼리를 날리는 방식
    private Long id;
    private String name;
    private LocalDateTime orderDate;
    private OrderStatus orderStatus;
    private Address address;

    private String itemName;
    private int orderPrice;
    private int count;

    public OrderFlatDto(Long id, String name, LocalDateTime orderDate, OrderStatus orderStatus, Address address, String itemName, int orderPrice, int count) {
        this.id = id;
        this.name = name;
        this.orderDate = orderDate;
        this.orderStatus = orderStatus;
        this.address = address;
        this.itemName = itemName;
        this.orderPrice = orderPrice;
        this.count = count;
    }
}
