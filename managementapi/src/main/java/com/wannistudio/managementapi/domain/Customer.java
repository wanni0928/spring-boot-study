package com.wannistudio.managementapi.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter
public class Customer {

    @Id @GeneratedValue
    @Column(name = "customer_id")
    private Long id;

    private String customerName;

    @Embedded
    private Place place;

    @OneToMany(mappedBy = "customer")
    private List<Order> orders = new ArrayList<>();
}
