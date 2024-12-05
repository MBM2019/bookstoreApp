package com.bookstore.app.domain;

import jakarta.validation.constraints.*;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.FieldNameConstants;

import java.math.BigDecimal;

@Data
@Builder
@FieldNameConstants
public class BookPageInputDto {

    @NotNull(message = "Page number can not be null")
    @Positive(message = "Page number cannot be neither negative nor zero")
    @Max(value = 3, message = "The maximum value for the page number is 3")
    @Digits(integer = 1, fraction = 0, message = "Wrong format of page number: only 1 digit is allowed(not decimals)")
    private BigDecimal pageNumber;
    @NotNull(message = "Page size can not be null")
    @Positive(message = "Page size cannot be neither negative nor zero")
    @Max(value = 14, message = "The maximum value for the page size is 14")
    @Digits(integer = 2, fraction = 0, message = "Wrong format of page size: only 2 digits are allowed(not decimals)")
    private BigDecimal pageSize;
}
