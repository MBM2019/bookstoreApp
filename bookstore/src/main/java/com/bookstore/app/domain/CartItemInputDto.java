package com.bookstore.app.domain;

import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.FieldNameConstants;

import java.math.BigDecimal;
import java.util.UUID;

@Data
@Builder
@FieldNameConstants
public class CartItemInputDto {

    @NotNull(message = "Quantity can not be null")
    @Positive(message = "Quantity cannot be neither negative nor zero")
    @Digits(integer = 3, fraction = 0, message = "Wrong format of quantity: only 3 digit are allowed(not decimals)")
    private BigDecimal quantity;
    @NotNull(message = "BookId can not be null")
    private UUID bookId;
}
