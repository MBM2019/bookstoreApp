package com.bookstore.app.controller;

import com.bookstore.app.domain.BookPageDto;
import com.bookstore.app.domain.BookPageInputDto;
import com.bookstore.app.mapper.DomainMapper;
import com.bookstore.app.service.BookService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

@Validated
@RestController
@RequestMapping("/books")
@AllArgsConstructor
public class BookController {

    private final BookService bookService;
    private final DomainMapper domainMapper;

    /**
     * Retrieval of a page containing the existing books
     * @param bookPageInputDto Parameter indicating the page number and the size of the page to be retrieved.
     * @return A page containing the amount of books indicated by the user in a specific page also indicated
     * by the user. The info retrieved of each book is:
     * - id
     * - title
     * - name
     * - bookPrice
     */
    @PostMapping(value = "/list")
    public BookPageDto getBookPage(@RequestBody @Valid BookPageInputDto bookPageInputDto) {
        return domainMapper.toBookPageDto(bookService.retrieveBooksPage(domainMapper.toBookPageInputBusiness(
                bookPageInputDto)));
    }
}
