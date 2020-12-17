package com.wannistudio.managementapi.service;

import com.wannistudio.managementapi.domain.Employee;
import com.wannistudio.managementapi.repository.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class EmployeeService {
    private final EmployeeRepository employeeRepository;

    @Transactional
    public Long join(Employee employee){
        validateDuplicateEmployee(employee);
        employeeRepository.save(employee);
        return employee.getId();
    }

    private void validateDuplicateEmployee(Employee employee) {
        List<Employee> employees = employeeRepository.findByName(employee.getEmployeeName());
        if(!employees.isEmpty()) {
            throw new IllegalStateException("이미 존재하는 직원입니다.");
        }
    }

    public List<Employee> findAll() {
        return employeeRepository.findAll();
    }

    public Employee findOne(Long employeeId) {
        return employeeRepository.findOne(employeeId);
    }
}
