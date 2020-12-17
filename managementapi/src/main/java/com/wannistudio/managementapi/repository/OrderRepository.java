package com.wannistudio.managementapi.repository;

import com.wannistudio.managementapi.domain.Order;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class OrderRepository {
    private final EntityManager em;

    public void save(Order order) {
        em.persist(order);
    }

    public Order findOne(Long orderId) {
        return em.find(Order.class, orderId);
    }

    public List<Order> findAllWithCustomer() {
        return em.createQuery("select o from Order o" +
                " join fetch o.customer c" +
                " join fetch o.shipper s", Order.class
        ).getResultList();
    }
}
