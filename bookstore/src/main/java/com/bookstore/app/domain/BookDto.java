package com.bookstore.app.domain;

import lombok.Builder;
import lombok.Data;

import java.util.UUID;

@Data
@Builder
public class BookDto {

    private UUID id;
    private String title;
    private String name;
    private double bookPrice;
}
