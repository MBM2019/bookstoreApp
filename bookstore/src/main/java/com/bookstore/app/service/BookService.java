package com.bookstore.app.service;

import com.bookstore.app.business.BookPageBusiness;
import com.bookstore.app.business.BookPageInputBusiness;
import com.bookstore.app.mapper.BusinessMapper;
import com.bookstore.app.repository.BookRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class BookService {

    private final BookRepository bookRepository;
    private final BusinessMapper businessMapper;

    public BookPageBusiness retrieveBooksPage(BookPageInputBusiness bookPageInputBusiness) {
        return businessMapper.toBookPageBusiness(bookRepository.findAll(PageRequest.of(bookPageInputBusiness
                        .getPageNumber(), bookPageInputBusiness.getPageSize(), Sort.by("title").ascending())));
    }
}
