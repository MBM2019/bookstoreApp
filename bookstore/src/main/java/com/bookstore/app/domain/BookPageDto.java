package com.bookstore.app.domain;

import com.bookstore.app.business.BookBusiness;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class BookPageDto {

    private int pageNumber;
    private int pageSize;
    private int total;
    private int totalPages;
    List<BookDto> content;
}
