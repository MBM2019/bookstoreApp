package com.bookstore.app.domain;

import lombok.Builder;
import lombok.Data;
import lombok.experimental.FieldNameConstants;

@Data
@Builder
@FieldNameConstants
public class UserDto {

    private String firstName;
    private String lastName;
    private String email;
}
