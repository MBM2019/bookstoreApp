package com.bookstore.app.domain;

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
    private List<BookDto> content;
}
