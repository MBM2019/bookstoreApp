package com.bookstore.app.business;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class BookPageInputBusiness {

    private int pageNumber;
    private int pageSize;
}
