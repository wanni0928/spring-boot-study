package com.wannistudio.managementapi.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Employee {

    @Id @GeneratedValue
    @Column(name = "employee_id")
    private Long id;
    private String employeeName;
    private String photo;
    private String note;
    private LocalDateTime birth;

    @Enumerated(EnumType.STRING)
    private WorkStatus workStatus; // [IN_OFFICE, QUIT]

    @OneToMany(mappedBy = "employee")
    private List<Order> orders = new ArrayList<>();

    public static Employee addEmployee(String employeeName, LocalDateTime birth, String photo, String note) {
        Employee employee = new Employee();
        employee.employeeName = employeeName;
        employee.birth = birth;
        employee.photo = photo;
        employee.note = note;
        employee.workStatus = WorkStatus.IN_OFFICE;
        return employee;
    }
}
