package com.bookstore.app.mapper;

import com.bookstore.app.business.*;
import com.bookstore.app.domain.*;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class DomainMapperTest {

    final DomainMapper domainMapper = Mappers.getMapper(DomainMapper.class);

    @Test
    public void toBookPageDto_NullValue() {
        assertNull(domainMapper.toBookPageDto(null));
    }

    @Test
    public void toBookPageDto_NullList() {
        BookPageDto bookPageDto = domainMapper.toBookPageDto(BookPageBusiness.builder().build());
        assertNotNull(bookPageDto);
        assertNotNull(bookPageDto.getContent());
        assertThat(bookPageDto.getContent()).isEmpty();
    }

    @Test
    public void toBookPageDto_EmptyList() {
        BookPageBusiness bookPageBusiness = BookPageBusiness.builder()
                .content(Collections.emptyList())
                .build();
        BookPageDto bookPageDto = domainMapper.toBookPageDto(bookPageBusiness);
        assertNotNull(bookPageDto);
        assertNotNull(bookPageDto.getContent());
        assertThat(bookPageDto.getContent()).isEmpty();
    }

    @Test
    public void toBookPageDto_NotEmptyList() {
        BookBusiness bookBusiness = BookBusiness.builder()
                .name("Roald Dahl")
                .bookPrice(45.23)
                .id(UUID.randomUUID())
                .title("Matilda")
                .build();
        BookPageBusiness bookPageBusiness = BookPageBusiness.builder()
                .content(List.of(bookBusiness))
                .pageNumber(1)
                .pageSize(1)
                .total(1)
                .totalPages(1)
                .build();
        BookPageDto bookPageDto = domainMapper.toBookPageDto(bookPageBusiness);
        assertNotNull(bookPageDto);
        assertNotNull(bookPageDto.getContent());
        assertThat(bookPageDto.getContent()).isNotEmpty();
        assertEquals(bookPageDto.getContent().size(), bookPageBusiness.getContent().size());
        assertEquals(bookPageDto.getContent().getFirst().getId(), bookPageBusiness.getContent().getFirst().getId());
        assertEquals(bookPageDto.getContent().getFirst().getBookPrice(), bookPageBusiness.getContent().getFirst()
                .getBookPrice());
        assertEquals(bookPageDto.getContent().getFirst().getName(), bookPageBusiness.getContent().getFirst().getName());
        assertEquals(bookPageDto.getContent().getFirst().getTitle(), bookPageBusiness.getContent().getFirst()
                .getTitle());
        assertEquals(bookPageDto.getPageSize(), bookPageBusiness.getPageSize());
        assertEquals(bookPageDto.getPageNumber(), bookPageBusiness.getPageNumber());
        assertEquals(bookPageDto.getTotalPages(), bookPageBusiness.getTotalPages());
        assertEquals(bookPageDto.getTotal(), bookPageBusiness.getTotal());
    }

    @Test
    public void toBookPageInputBusinessTest() {
        BookPageInputDto bookPageInputDto = BookPageInputDto.builder()
                .pageNumber(new BigDecimal(1))
                .pageSize(new BigDecimal(5))
                .build();
        BookPageInputBusiness bookPageInputBusiness = domainMapper.toBookPageInputBusiness(bookPageInputDto);
        assertEquals(bookPageInputBusiness.getPageNumber(), 0);
        assertEquals(bookPageInputBusiness.getPageSize(), bookPageInputDto.getPageSize().intValue());
    }

    @Test
    public void toUserDto_NullValue() {
        assertNull(domainMapper.toUserDto(null));
    }

    @Test
    public void toUserDto_NotNullValue1() {
        UserDto userDto = domainMapper.toUserDto(UserBusiness.builder().build());
        assertNotNull(userDto);
        assertNull(userDto.getEmail());
        assertNull(userDto.getFirstName());
        assertNull(userDto.getLastName());
    }

    @Test
    public void toUserDto_NotNullValue2() {
        UserBusiness userEntity = mock(UserBusiness.class);
        when(userEntity.getEmail()).thenReturn("fred@hyt.com");
        when(userEntity.getFirstName()).thenReturn("Fred");
        when(userEntity.getLastName()).thenReturn("Hyton");
        UserDto userDto = domainMapper.toUserDto(userEntity);
        assertNotNull(userDto);
        assertNotNull(userDto.getEmail());
        assertEquals(userDto.getEmail(), userEntity.getEmail());
        assertNotNull(userDto.getFirstName());
        assertEquals(userDto.getFirstName(), userEntity.getFirstName());
        assertNotNull(userDto.getLastName());
        assertEquals(userDto.getLastName(), userEntity.getLastName());
    }

    @Test
    public void toRegisterInputBusiness_NullValue() {
        assertNull(domainMapper.toRegisterInputBusiness(null));
    }

    @Test
    public void toRegisterInputBusiness_NotNullValue1() {
        RegisterInputBusiness registerInputBusiness = domainMapper.toRegisterInputBusiness(RegisterInputDto.builder()
                .build());
        assertNotNull(registerInputBusiness);
        assertNull(registerInputBusiness.getEmail());
        assertNull(registerInputBusiness.getFirstName());
        assertNull(registerInputBusiness.getLastName());
        assertNull(registerInputBusiness.getPassword());
    }

    @Test
    public void toRegisterInputBusiness_NotNullValue2() {
        RegisterInputDto registerInputDto = mock(RegisterInputDto.class);
        when(registerInputDto.getEmail()).thenReturn("fred@hyt.com");
        when(registerInputDto.getFirstName()).thenReturn("Fred");
        when(registerInputDto.getLastName()).thenReturn("Hyton");
        when(registerInputDto.getPassword()).thenReturn("Kfrt2349%");
        RegisterInputBusiness registerInputBusiness = domainMapper.toRegisterInputBusiness(registerInputDto);
        assertNotNull(registerInputBusiness);
        assertNotNull(registerInputBusiness.getEmail());
        assertEquals(registerInputBusiness.getEmail(), registerInputDto.getEmail());
        assertNotNull(registerInputBusiness.getFirstName());
        assertEquals(registerInputBusiness.getFirstName(), registerInputDto.getFirstName());
        assertNotNull(registerInputBusiness.getLastName());
        assertEquals(registerInputBusiness.getLastName(), registerInputDto.getLastName());
        assertNotNull(registerInputBusiness.getPassword());
        assertEquals(registerInputBusiness.getPassword(), registerInputDto.getPassword());
    }

    @Test
    public void toCartItemInputBusinessTest_NullValue() {
        assertNull(domainMapper.toCartItemInputBusiness(null));
    }

    @Test
    public void toCartItemInputBusinessTest_NotNullValue1() {
        CartItemInputBusiness cartItemInputBusiness = domainMapper.toCartItemInputBusiness(CartItemInputDto.builder()
                .build());
        assertNotNull(cartItemInputBusiness);
        assertEquals(cartItemInputBusiness.getQuantity(), 0);
        assertNull(cartItemInputBusiness.getBookId());
    }

    @Test
    public void toCartItemInputBusinessTest_NotNullValue2() {
        CartItemInputDto cartItemInputDto = CartItemInputDto.builder()
                .bookId(UUID.randomUUID())
                .quantity(new BigDecimal(5))
                .build();
        CartItemInputBusiness cartItemInputBusiness = domainMapper.toCartItemInputBusiness(cartItemInputDto);
        assertNotNull(cartItemInputBusiness);
        assertEquals(cartItemInputBusiness.getQuantity(), cartItemInputDto.getQuantity().intValue());
        assertNotNull(cartItemInputBusiness.getBookId());
        assertEquals(cartItemInputBusiness.getBookId(), cartItemInputDto.getBookId());
    }

    @Test
    public void toCheckOutInputBusiness_NullValue() {
        assertNull(domainMapper.toCheckOutInputBusiness(null));
    }

    @Test
    public void toCheckOutInputBusiness_NotNullValue1() {
        CheckOutInputBusiness checkOutInputBusiness = domainMapper.toCheckOutInputBusiness(CheckOutInputDto.builder()
                .build());
        assertNotNull(checkOutInputBusiness);
        assertEquals(checkOutInputBusiness.getOrderPrice(), 0.0);
        assertNull(checkOutInputBusiness.getCartItemInputList());
    }

    @Test
    public void toCheckOutInputBusiness_NotNullValue2() {
        CheckOutInputDto checkOutInputDto = CheckOutInputDto.builder()
                .orderPrice(56.34)
                .cartItemInputList(new ArrayList<>())
                .build();
        CheckOutInputBusiness checkOutInputBusiness = domainMapper.toCheckOutInputBusiness(checkOutInputDto);
        assertNotNull(checkOutInputBusiness);
        assertEquals(checkOutInputBusiness.getOrderPrice(), checkOutInputDto.getOrderPrice());
        assertNotNull(checkOutInputBusiness.getCartItemInputList());
        assertThat(checkOutInputBusiness.getCartItemInputList()).isEmpty();
    }

    @Test
    public void toCheckOutInputBusiness_NotNullValue3() {
        CartItemInputDto cartItemInputDto = mock(CartItemInputDto.class);
        CheckOutInputDto checkOutInputDto = CheckOutInputDto.builder()
                .orderPrice(56.34)
                .cartItemInputList(List.of(cartItemInputDto))
                .build();
        CheckOutInputBusiness checkOutInputBusiness = domainMapper.toCheckOutInputBusiness(checkOutInputDto);
        assertNotNull(checkOutInputBusiness);
        assertEquals(checkOutInputBusiness.getOrderPrice(), checkOutInputDto.getOrderPrice());
        assertNotNull(checkOutInputBusiness.getCartItemInputList());
        assertThat(checkOutInputBusiness.getCartItemInputList()).isNotEmpty();
        assertEquals(checkOutInputBusiness.getCartItemInputList().size(), checkOutInputDto.getCartItemInputList()
                .size());
    }
}
