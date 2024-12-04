package com.bookstore.app.domain;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Positive;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.FieldNameConstants;

import java.util.List;

@Data
@Builder
@FieldNameConstants
public class CheckOutInputDto {

    @Positive(message = "The price cannot be neither negative nor zero")
    @Digits(integer = 9, fraction = 2, message = "Wrong format of price")
    private double orderPrice;
    @NotEmpty(message = "The cartItemInputList cannot be empty")
    @Valid
    private List<CartItemInputDto> cartItemInputList;
}
