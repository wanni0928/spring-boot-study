package com.wannistudio.managementapi.repository;

import com.wannistudio.managementapi.domain.Employee;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class EmployeeRepository {

    private final EntityManager em;

    public void save(Employee employee) {
        em.persist(employee);
    }

    public Employee findOne(Long employeeId) {
        return em.find(Employee.class, employeeId);
    }

    public List<Employee> findAll() {
        return em.createQuery("select e from Employee e", Employee.class)
                .getResultList();
    }

    public List<Employee> findByName(String name) {
        return em.createQuery("select e from Employee e where e.EmployeeName = :name", Employee.class)
                .setParameter("name", name)
                .getResultList();
    }
}
