package com.bookstore.app.business;

import lombok.Builder;
import lombok.Data;

import java.util.UUID;

@Data
@Builder
public class CartItemInputBusiness {

    private int quantity;
    private UUID bookId;
}
