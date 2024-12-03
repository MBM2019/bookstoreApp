package com.bookstore.app.business;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class BookPageBusiness {

    private int pageNumber;
    private int pageSize;
    private int total;
    private int totalPages;
    List<BookBusiness> content;
}
