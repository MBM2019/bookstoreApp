package com.bookstore.app.domain;

import lombok.Builder;
import lombok.Data;
import lombok.experimental.FieldNameConstants;

@Data
@Builder
@FieldNameConstants
public class RegisterInputDto {

    //TODO add validation

    private String firstName;
    private String lastName;
    private String email;
    private String password;

}
