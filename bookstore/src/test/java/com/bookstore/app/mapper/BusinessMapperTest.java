package com.bookstore.app.mapper;

import com.bookstore.app.business.BookBusiness;
import com.bookstore.app.business.BookPageBusiness;
import com.bookstore.app.entity.AuthorEntity;
import com.bookstore.app.entity.BookEntity;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;
import org.springframework.data.domain.*;

import java.util.Collections;
import java.util.List;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class BusinessMapperTest {

    final BusinessMapper businessMapper = Mappers.getMapper(BusinessMapper.class);

    @Test
    public void toBookBusinessTest_NullValue() {
        assertNull(businessMapper.toBookBusiness(null));
    }

    @Test
    public void toBookBusinessTest_NotNullValue1() {
        BookBusiness bookBusiness = businessMapper.toBookBusiness(new BookEntity());
        assertNotNull(bookBusiness);
        assertNull(bookBusiness.getId());
        assertNull(bookBusiness.getTitle());
        assertNull(bookBusiness.getName());
        assertEquals(bookBusiness.getBookPrice(), 0.0);
    }

    @Test
    public void toBookBusinessTest_NotNullValue2() {

        BookEntity bookEntity = mock(BookEntity.class);
        when(bookEntity.getBookPrice()).thenReturn(25.57);
        UUID id = UUID.randomUUID();
        when(bookEntity.getId()).thenReturn(id);
        when(bookEntity.getTitle()).thenReturn("Matilda");
        BookBusiness bookBusiness = businessMapper.toBookBusiness(bookEntity);
        assertNotNull(bookBusiness);
        assertNotNull(bookBusiness.getId());
        assertEquals(bookBusiness.getId(), id);
        assertNotNull(bookBusiness.getTitle());
        assertEquals(bookBusiness.getTitle(), bookEntity.getTitle());
        assertNull(bookBusiness.getName());
        assertEquals(bookBusiness.getBookPrice(), bookEntity.getBookPrice());
    }

    @Test
    public void toBookBusinessTest_AuthorNotNull1() {

        BookEntity bookEntity = mock(BookEntity.class);
        AuthorEntity authorEntity = mock(AuthorEntity.class);
        when(bookEntity.getAuthor()).thenReturn(authorEntity);
        BookBusiness bookBusiness = businessMapper.toBookBusiness(bookEntity);
        assertNotNull(bookBusiness);
        assertNull(bookBusiness.getName());
    }

    @Test
    public void toBookBusinessTest_AuthorNotNull2() {

        BookEntity bookEntity = mock(BookEntity.class);
        AuthorEntity authorEntity = mock(AuthorEntity.class);
        when(bookEntity.getAuthor()).thenReturn(authorEntity);
        when(authorEntity.getFirstName()).thenReturn("Roald");
        BookBusiness bookBusiness = businessMapper.toBookBusiness(bookEntity);
        assertNotNull(bookBusiness);
        assertNotNull(bookBusiness.getName());
        assertEquals(bookBusiness.getName(), bookEntity.getAuthor().getFirstName());
    }

    @Test
    public void toBookBusinessTest_AuthorNotNull3() {

        BookEntity bookEntity = mock(BookEntity.class);
        AuthorEntity authorEntity = mock(AuthorEntity.class);
        when(bookEntity.getAuthor()).thenReturn(authorEntity);
        when(authorEntity.getLastName()).thenReturn("Dahl");
        BookBusiness bookBusiness = businessMapper.toBookBusiness(bookEntity);
        assertNotNull(bookBusiness);
        assertNotNull(bookBusiness.getName());
    }

    @Test
    public void toBookBusinessTest_AuthorNotNull4() {

        BookEntity bookEntity = mock(BookEntity.class);
        AuthorEntity authorEntity = mock(AuthorEntity.class);
        when(bookEntity.getAuthor()).thenReturn(authorEntity);
        when(authorEntity.getFirstName()).thenReturn("Roald");
        when(authorEntity.getLastName()).thenReturn("Dahl");
        BookBusiness bookBusiness = businessMapper.toBookBusiness(bookEntity);
        assertNotNull(bookBusiness);
        assertNotNull(bookBusiness.getName());
        assertEquals(bookBusiness.getName(), bookEntity.getAuthor().getFirstName().concat(" ").concat(bookEntity
                .getAuthor().getLastName()));
    }

    @Test
    public void toBookPageBusiness_NullValue()  {
        assertNull(businessMapper.toBookPageBusiness(null));
    }

    @Test
    public void toBookPageBusiness_EmptyList()  {

        Page<BookEntity> page = new PageImpl<>(Collections.emptyList(), PageRequest.of(0, 5, Sort
                .by("title").ascending()), 10);
        BookPageBusiness bookPageBusiness = businessMapper.toBookPageBusiness(page);
        assertNotNull(bookPageBusiness);
        assertNotNull(bookPageBusiness.getContent());
        assertThat(bookPageBusiness.getContent()).isEmpty();
        assertEquals(bookPageBusiness.getPageNumber(), 1);
        assertEquals(bookPageBusiness.getPageSize(), 5);
        assertEquals(bookPageBusiness.getTotalPages(), 2);
        assertEquals(bookPageBusiness.getTotal(), 10);
    }

    @Test
    public void toBookPageBusiness_NotEmptyList()  {

        BookEntity bookEntity1 = mock(BookEntity.class), bookEntity2 = mock(BookEntity.class);
        Page<BookEntity> bookEntityPage = new PageImpl<>(List.of(bookEntity1, bookEntity2), PageRequest.of(0,
                5, Sort.by("title").ascending()), 10);
        BookPageBusiness bookPageBusiness = businessMapper.toBookPageBusiness(bookEntityPage);
        assertNotNull(bookPageBusiness);
        assertNotNull(bookPageBusiness.getContent());
        assertThat(bookPageBusiness.getContent()).isNotEmpty();
        assertEquals(bookPageBusiness.getContent().size(), bookEntityPage.getContent().size());
    }
}
