package com.bookstore.app.service.impl;

import com.bookstore.app.business.BookPageBusiness;
import com.bookstore.app.business.BookPageInputBusiness;
import com.bookstore.app.mapper.BusinessMapper;
import com.bookstore.app.repository.BookRepository;
import com.bookstore.app.service.BookService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;
    private final BusinessMapper businessMapper;

    public BookPageBusiness retrieveBooksPage(BookPageInputBusiness bookPageInputBusiness) {
        return businessMapper.toBookPageBusiness(bookRepository.findAll(PageRequest.of(bookPageInputBusiness
                        .getPageNumber(), bookPageInputBusiness.getPageSize(), Sort.by("title").ascending())));
    }
}
