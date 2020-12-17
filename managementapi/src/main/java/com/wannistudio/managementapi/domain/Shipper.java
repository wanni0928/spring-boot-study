package com.wannistudio.managementapi.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter
public class Shipper {
    @Id @GeneratedValue
    @Column(name = "shipper_id")
    private Long id;

    private String shipperName;
    private String phone;

    @OneToMany(mappedBy = "shipper")
    private List<Order> orders = new ArrayList<>();
}
