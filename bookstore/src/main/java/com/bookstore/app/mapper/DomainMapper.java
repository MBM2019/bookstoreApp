package com.bookstore.app.mapper;

import com.bookstore.app.business.*;
import com.bookstore.app.domain.*;
import org.mapstruct.*;
import java.math.BigDecimal;
import java.util.Collections;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface DomainMapper {

    BookPageDto toBookPageDto(BookPageBusiness bookPageBusiness);

    @AfterMapping
    default void mapContentWhenNullValue(final BookPageBusiness bookPageBusiness, @MappingTarget BookPageDto
            .BookPageDtoBuilder builder) {
        if (bookPageBusiness.getContent() == null) {
            builder.content(Collections.emptyList());
        }
    }

    BookPageInputBusiness toBookPageInputBusiness(BookPageInputDto bookPageInputDto);

    @AfterMapping
    default void mapPageNumber(final BookPageInputDto bookPageInputDto, @MappingTarget BookPageInputBusiness
            .BookPageInputBusinessBuilder builder) {
        builder.pageNumber(mapPageNumber(bookPageInputDto.getPageNumber()));
    }

    RegisterInputBusiness toRegisterInputBusiness(RegisterInputDto registerInputDto);

    UserDto toUserDto(UserBusiness userBusiness);

    CheckOutInputBusiness toCheckOutInputBusiness(CheckOutInputDto checkOutInputDto);

    CartItemInputBusiness toCartItemInputBusiness(CartItemInputDto cartItemInputDto);

    private static int mapPageNumber(BigDecimal pageNumber) {
        return pageNumber.intValue() - 1;
    }
}
