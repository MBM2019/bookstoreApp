package com.bookstore.app.business;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserBusiness {

    private String firstName;
    private String lastName;
    private String email;
}
