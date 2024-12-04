package com.bookstore.app.service;

import com.bookstore.app.business.BookPageInputBusiness;
import com.bookstore.app.entity.BookEntity;
import com.bookstore.app.mapper.BusinessMapper;
import com.bookstore.app.mapper.BusinessMapperImpl;
import com.bookstore.app.repository.BookRepository;
import com.bookstore.app.service.impl.BookServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.*;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class BookServiceImplTest {

    @Mock
    private BookRepository bookRepository;
    private BookServiceImpl bookServiceImpl;

    @BeforeEach
    public void setup() {
        BusinessMapper businessMapper = new BusinessMapperImpl();
        bookServiceImpl = new BookServiceImpl(bookRepository, businessMapper);
    }

    @Test
    public void test() {
        BookEntity bookEntity1 = mock(BookEntity.class), bookEntity2 = mock(BookEntity.class);
        Page<BookEntity> bookEntityPage = new PageImpl<>(List.of(bookEntity1, bookEntity2), PageRequest.of(0,
                5, Sort.by("title").ascending()), 10);
        when(bookRepository.findAll(any(Pageable.class))).thenReturn(bookEntityPage);
        BookPageInputBusiness bookPageInputBusiness = BookPageInputBusiness.builder()
                .pageNumber(0)
                .pageSize(5)
                .build();
        assertNotNull(bookServiceImpl.retrieveBooksPage(bookPageInputBusiness));
    }
}
