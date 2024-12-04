package com.bookstore.app.service;

import com.bookstore.app.business.BookPageBusiness;
import com.bookstore.app.business.BookPageInputBusiness;

public interface BookService {

    public BookPageBusiness retrieveBooksPage(BookPageInputBusiness bookPageInputBusiness);
}
