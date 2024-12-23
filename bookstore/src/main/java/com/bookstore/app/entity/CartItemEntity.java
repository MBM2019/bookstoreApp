package com.bookstore.app.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@EqualsAndHashCode(callSuper = true)
@Entity(name = "cart_item")
public class CartItemEntity extends AbstractEntity {

    @Column(name = "quantity", nullable = false)
    private int quantity;

    @ManyToOne(cascade = CascadeType.MERGE, fetch = FetchType.LAZY)
    @JoinColumn(name = "cart_shop", referencedColumnName = "id")
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private CartShopEntity cartShop;

    @ManyToOne(cascade = CascadeType.MERGE, fetch = FetchType.LAZY)
    @JoinColumn(name = "book", referencedColumnName = "id")
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private BookEntity book;
}
