package com.wannistudio.managementapi.repository;

import com.wannistudio.managementapi.domain.Customer;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class CustomerRepository {
    private final EntityManager em;

    public void save(Customer customer) {
        em.persist(customer);
    }

    public Customer findOne(Long customerId) {
        return em.find(Customer.class, customerId);
    }

    public List<Customer> findAll() {
        return em.createQuery("select c from Customer c", Customer.class)
                .getResultList();
    }

    public List<Customer> findByName(String name) {
        return em.createQuery("select c from Customer c where c.name = :name", Customer.class)
                .setParameter("name", name)
                .getResultList();
    }
}
