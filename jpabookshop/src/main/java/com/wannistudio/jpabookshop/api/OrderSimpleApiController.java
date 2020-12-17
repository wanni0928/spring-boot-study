package com.wannistudio.jpabookshop.api;

import com.wannistudio.jpabookshop.domain.Order;
import com.wannistudio.jpabookshop.repository.OrderRepository;
import com.wannistudio.jpabookshop.repository.OrderSearch;
import com.wannistudio.jpabookshop.repository.order.simplequery.OrderSimpleQueryDto;
import com.wannistudio.jpabookshop.repository.order.simplequery.OrderSimpleQueryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

/**
 * xToOne(ManyToOne, OneToMany)
 * Order
 * Order -> Member
 * Order -> Delivery
 */
@RestController
@RequiredArgsConstructor
public class OrderSimpleApiController {
    private final OrderRepository orderRepository;
    private final OrderSimpleQueryRepository orderSimpleQueryRepository;

    @GetMapping("/api/v1/simple-orders") // 무한루프...
    // 해결법 1. 양방향 매핑된 필드에 @JsonIgnore -> No serializer found Exception
    //
    public List<Order> ordersV1() {
        List<Order> orders = orderRepository.findAllByString(new OrderSearch());

        for (Order order : orders) {
            order.getMember().getName(); // Lazy 초기화
            order.getDelivery().getOrder();
        }
        return orders;
    }

    @GetMapping("/api/v2/simple-orders") // 쿼리가 너무 많이 나감.
    public List<OrderSimpleQueryDto> ordersV2() {
        //N + 1 문제 -> 1 + 회원N + 배송N
        List<Order> orders = orderRepository.findAllByString(new OrderSearch());
        return orders.stream()
                .map(OrderSimpleQueryDto::new)
                .collect(Collectors.toList());
    }

    @GetMapping("/api/v3/simple-orders")
    public List<OrderSimpleQueryDto> ordersV3() {
        // 성능 최적화는 v4 보다 조금 떨어지지만, 재사용성이 높다.
        List<Order> orders = orderRepository.findAllWithMemberDelivery();
        return orders.stream()
                .map(OrderSimpleQueryDto::new)
                .collect(Collectors.toList());
    }

    @GetMapping("/api/v4/simple-orders")
    public List<OrderSimpleQueryDto> ordersV4() {
        // 성능 최적화는 v3 보다 더 좋지만, 재사용성이 fix 되있다.
        return orderSimpleQueryRepository.findOrderDtos();
    }


}
