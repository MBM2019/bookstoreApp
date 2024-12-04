package com.bookstore.app.business;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class CheckOutInputBusiness {

    private double orderPrice;
    private List<CartItemInputBusiness> cartItemInputList;

}
