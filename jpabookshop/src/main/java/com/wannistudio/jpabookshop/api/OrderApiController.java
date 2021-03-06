package com.wannistudio.jpabookshop.api;

import com.wannistudio.jpabookshop.domain.Address;
import com.wannistudio.jpabookshop.domain.Order;
import com.wannistudio.jpabookshop.domain.OrderItem;
import com.wannistudio.jpabookshop.domain.OrderStatus;
import com.wannistudio.jpabookshop.repository.OrderRepository;
import com.wannistudio.jpabookshop.repository.OrderSearch;
import com.wannistudio.jpabookshop.repository.order.query.OrderFlatDto;
import com.wannistudio.jpabookshop.repository.order.query.OrderItemQueryDto;
import com.wannistudio.jpabookshop.repository.order.query.OrderQueryDto;
import com.wannistudio.jpabookshop.repository.order.query.OrderQueryRepository;
import lombok.Data;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.*;
import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.toList;
import static org.yaml.snakeyaml.nodes.NodeId.mapping;

@RestController
@RequiredArgsConstructor
public class OrderApiController {

    private final OrderRepository orderRepository;
    private final OrderQueryRepository orderQueryRepository;

    @GetMapping("/api/v1/orders") // @JsonIgnore를 따로 찾아서 해야하는 번거로 움 + 엔티티 직접노출(변경에 열려있음...)
    public List<Order> ordersV1() {
        List<Order> orders = orderRepository.findAllByString(new OrderSearch());
        for (Order order : orders) {
            order.getMember().getName();
            order.getDelivery().getAddress();
            List<OrderItem> orderItems = order.getOrderItems();
            orderItems.forEach(orderItem -> {
                orderItem.getItem().getName();
            });
        }
        return orders;
    }

    @GetMapping("/api/v2/orders") // 엔티티 직접 노출을 막아서, 변경에 닫혀있는 형태를 유지하였으나, 데이터 조회 당 던져지는 쿼리수가 너무 많다. (성능저하 가능성)
    public List<OrderDto> ordersV2() {
        List<Order> orders = orderRepository.findAllByString(new OrderSearch());
        return orders.stream()
                .map(OrderDto::new)
                .collect(toList());
    }

    @GetMapping("/api/v3/orders")
    // 외래키에 해당하는 엔티티들을 다 join 해서 데이터를 반환한다. (orderItem 수 만큼 같은 order 엔티티를 반복해서 보여준다. -> 데이터 용량이 2배로 증가한다.)
    // jpql에 distinct를 넣어서, 해결
    // 단점 : 페이징이 불가능하다.
    // 컬렉션 fetch join을 사용하면 페이징이 불가능하다.
    public List<OrderDto> ordersV3() {
        List<Order> orders = orderRepository.findAllWithItem();
        return orders.stream()
                .map(OrderDto::new)
                .collect(toList());
    }

    @GetMapping("/api/v3.1/orders")
    public List<OrderDto> ordersV3_1(@RequestParam(value = "offset", defaultValue = "0") int offset,
                                     @RequestParam(value = "limit", defaultValue = "100") int limit) {
        List<Order> orders = orderRepository.findAllWithMemberDelivery(offset, limit);
        return orders.stream()
                .map(OrderDto::new)
                .collect(toList());
    }

    @GetMapping("/api/v4/orders")
    public List<OrderQueryDto> ordersV4() { // n+1 문제 발생.
        return orderQueryRepository.findOrderQueryDtos();
    }

    @GetMapping("/api/v5/orders")
    public List<OrderQueryDto> ordersV5() {
        return orderQueryRepository.findAllByDto_optimization();
    }

    @GetMapping("/api/v6/orders")
    public List<OrderQueryDto> ordersV6() { // 데이터가 살짝 뻥튀기 되었지만, 한방 쿼리로 해결된다.
        List<OrderFlatDto> flats = orderQueryRepository.findAllByDto_flat();
        return flats.stream()
                    .collect(
                            groupingBy(o -> new OrderQueryDto(o.getId(), o.getName(), o.getOrderDate(), o.getOrderStatus(), o.getAddress()),
                            mapping(o -> new OrderItemQueryDto(o.getId(), o.getItemName(), o.getOrderPrice(), o.getCount()), toList())))
                    .entrySet().stream()
                        .map(e -> new OrderQueryDto(e.getKey().getId(),e.getKey().getName(), e.getKey().getOrderDate(), e.getKey().getOrderStatus(),
                                e.getKey().getAddress(), e.getValue()))
                .collect(toList());
    }

    // 페이징 처리
    @Getter
    static class OrderDto {
        private Long orderId;
        private String name;
        private LocalDateTime orderDate;
        private OrderStatus orderStatus;
        private Address address;
        private List<OrderItemDto> orderItems; // Dto 안의 엔티티도 Dto로 변환해야 한다.

        public OrderDto(Order order) {
            orderId = order.getId();
            name = order.getMember().getName();
            orderDate = order.getOrderDate();
            orderStatus = order.getStatus();
            address = order.getMember().getAddress();
            order.getOrderItems().forEach(orderItem -> orderItem.getItem().getName());
            orderItems = order.getOrderItems().stream()
                .map(OrderItemDto::new)
                .collect(toList());
        }
    }

    @Getter
    static class OrderItemDto {
        private String itemName;
        private int orderPrice;
        private int count;

        public OrderItemDto(OrderItem orderItem) {
            itemName = orderItem.getItem().getName();
            orderPrice = orderItem.getOrderPrice();
            count = orderItem.getCount();
        }
    }
}
