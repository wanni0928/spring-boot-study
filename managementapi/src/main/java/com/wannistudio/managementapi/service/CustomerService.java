package com.wannistudio.managementapi.service;

import com.wannistudio.managementapi.domain.Customer;
import com.wannistudio.managementapi.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class CustomerService {
    private final CustomerRepository customerRepository;

    // 고객 등록
    @Transactional
    public Long join(Customer customer) {
        validateDuplicateCustomer(customer);
        customerRepository.save(customer);
        return customer.getId();
    }

    private void validateDuplicateCustomer(Customer customer) {
        List<Customer> customers = customerRepository.findByName(customer.getCustomerName());
        if(!customers.isEmpty()) {
            throw new IllegalStateException("이미 존재하는 고객입니다.");
        }
    }

    // 전체 고객 조회
    public List<Customer> findCustomers() {
        return customerRepository.findAll();
    }

    public Customer findOne(Long customerId) {
        return customerRepository.findOne(customerId);
    }
}
