package com.wannistudio.managementapi.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter
public class Product {
    @Id @GeneratedValue
    private Long id;
    private String productName;
    private int productQuantity;
    private int price;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id")
    private Category category;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "supplier_id")
    private Supplier supplier;

    @OneToMany(mappedBy = "product")
    List<OrderDetail> orderDetails = new ArrayList<>();

    public void removeStock(int quantity) {
        this.productQuantity -= quantity;
    }

    public void addStock(int quantity) {
        this.productQuantity += quantity;
    }
}
