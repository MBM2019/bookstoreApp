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

    @ManyToOne(cascade = CascadeType.MERGE, fetch = FetchType.LAZY)
    @JoinColumn(name = "users", referencedColumnName = "id")
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private UserEntity users;

    @OneToMany(mappedBy = "cartShop", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<CartItemEntity> cartItemEntityList;
}

