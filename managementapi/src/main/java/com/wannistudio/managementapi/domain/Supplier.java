package com.wannistudio.managementapi.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter @Setter
public class Supplier {
    @Id @GeneratedValue
    @Column(name = "supplier_id")
    private Long id;

    private String supplierName;
    private String contactName;
    private String phone;

    @Embedded
    private Place place;
}
