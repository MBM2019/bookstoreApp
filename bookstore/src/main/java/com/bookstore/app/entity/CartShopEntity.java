package com.bookstore.app.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.util.List;

@Data
@EqualsAndHashCode(callSuper = true)
@Entity(name = "cart_shop")
public class CartShopEntity extends AbstractEntity {

    @Column(name = "order_price", nullable = false)
    private double orderPrice;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "customer", referencedColumnName = "id")
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private CustomerEntity customer;

    @OneToMany(mappedBy = "cartShop", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<CartItemEntity> cartItemEntityList;
}

