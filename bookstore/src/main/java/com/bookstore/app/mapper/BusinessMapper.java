package com.bookstore.app.mapper;

import com.bookstore.app.business.BookBusiness;
import com.bookstore.app.business.BookPageBusiness;
import com.bookstore.app.business.UserBusiness;
import com.bookstore.app.entity.AuthorEntity;
import com.bookstore.app.entity.BookEntity;
import com.bookstore.app.entity.UserEntity;
import org.mapstruct.*;
import org.springframework.data.domain.Page;

import java.util.Collections;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface BusinessMapper {

    BookBusiness toBookBusiness(BookEntity bookEntity);

    @AfterMapping
    default void mapName(final BookEntity bookEntity, @MappingTarget BookBusiness.BookBusinessBuilder
            builder) {
        builder.name(getName(bookEntity));
    }

    @Mapping(target = "pageSize", source = "pageable.pageSize")
    @Mapping(target = "total", source = "totalElements")
    BookPageBusiness toBookPageBusiness(Page<BookEntity> bookEntityPage);

    @AfterMapping
    default void mapPageNumber(final Page<BookEntity> bookEntityPage, @MappingTarget BookPageBusiness
            .BookPageBusinessBuilder builder) {
        builder.pageNumber(bookEntityPage.getPageable().getPageNumber() + 1);
        if (!bookEntityPage.hasContent()) {
            builder.content(Collections.emptyList());
        }
    }

    UserBusiness toUserBusiness(UserEntity userEntity);

    private static String getName(final BookEntity bookEntity) {
        return bookEntity.getAuthor() != null ? concatenateName(bookEntity.getAuthor()) : null;
    }

    private static String concatenateName(final AuthorEntity authorEntity) {
        if (authorEntity.getLastName() != null && authorEntity.getFirstName() != null) {
            return authorEntity.getFirstName().concat(" ").concat(authorEntity.getLastName());
        }
        if (authorEntity.getLastName() != null) {
            return authorEntity.getLastName();
        }
        if (authorEntity.getFirstName() != null) {
            return authorEntity.getFirstName();
        }
        return null;
    }
}
