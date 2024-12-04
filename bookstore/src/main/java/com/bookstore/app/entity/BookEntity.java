package com.bookstore.app.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@EqualsAndHashCode(callSuper = true)
@Entity(name = "book")
public class BookEntity extends AbstractEntity {

    @Column(name = "title")
    private String title;

    @ManyToOne(cascade = CascadeType.MERGE, fetch = FetchType.LAZY)
    @JoinColumn(name = "author", referencedColumnName = "id")
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private AuthorEntity author;

    @Column(name = "book_price", nullable = false)
    private double bookPrice;
}
