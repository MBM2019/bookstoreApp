package com.bookstore.app.mapper;

import com.bookstore.app.business.BookBusiness;
import com.bookstore.app.business.BookPageBusiness;
import com.bookstore.app.business.BookPageInputBusiness;
import com.bookstore.app.domain.BookPageDto;
import com.bookstore.app.domain.BookPageInputDto;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

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
}
