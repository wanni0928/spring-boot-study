package com.wannistudio.managementapi.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter
public class Employee {

    @Id @GeneratedValue
    @Column(name = "employee_id")
    private Long id;
    private String EmployeeName;
    private String photo;
    private String note;
    private LocalDateTime birth;

    @Enumerated(EnumType.STRING)
    private WorkStatus workStatus; // [IN_OFFICE, QUIT]

    @OneToMany(mappedBy = "employee")
    private List<Order> orders = new ArrayList<>();
}
