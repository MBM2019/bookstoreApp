package com.bookstore.app.business;

import lombok.Builder;
import lombok.Data;

import java.util.UUID;

@Data
@Builder
public class BookBusiness {

    private UUID id;
    private String title;
    private String name;
    private double bookPrice;
}
