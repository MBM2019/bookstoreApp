package com.bookstore.app.domain;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.FieldNameConstants;

import java.util.List;

@Data
@Builder
@FieldNameConstants
public class CheckOutInputDto {

    @PositiveOrZero(message = "The price can not be negative")
    @Digits(integer = 9, fraction = 2, message = "Wrong format of price")
    private double orderPrice;
    @NotEmpty
    @Valid
    private List<CartItemInputDto> cartItemInputList;
}
