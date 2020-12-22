package com.wannistudio.jpabookshop.repository.order.simplequery;

import com.wannistudio.jpabookshop.repository.order.query.OrderItemQueryDto;
import com.wannistudio.jpabookshop.repository.order.query.OrderQueryDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class OrderSimpleQueryRepository {

    private final EntityManager em;

    public List<OrderSimpleQueryDto> findOrderDtos() { // OrderSimpleQueryDto는 엔티티 객체가 아니다. 여기서 o는 식별자에 불가하다.
        return em.createQuery("select new com.wannistudio.jpabookshop.repository.order.simplequery.OrderSimpleQueryDto(o.id, m.name, o.orderDate, o.status, d.address)" +
                " from Order o" +
                " join o.member m" +
                " join o.delivery d", OrderSimpleQueryDto.class)
                .getResultList();
    }
}
