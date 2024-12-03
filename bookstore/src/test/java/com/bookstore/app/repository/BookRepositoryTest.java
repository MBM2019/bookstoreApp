package com.bookstore.app.repository;

import com.bookstore.app.entity.BookEntity;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@DataJpaTest
public class BookRepositoryTest {

    @Autowired
    public BookRepository bookRepository;

    /**
     * Test the relation between BookEntity and AuthorEntity
     */
    @Test
    public void test() {
        Page<BookEntity> bookEntityPage = bookRepository.findAll(PageRequest.of(0, 5, Sort
                .by("title").ascending()));
        assertNotNull(bookEntityPage);
        assertNotNull(bookEntityPage.getContent().getFirst().getAuthor());
    }
}
