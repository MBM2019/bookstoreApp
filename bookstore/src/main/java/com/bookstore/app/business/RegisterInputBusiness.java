package com.bookstore.app.business;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class RegisterInputBusiness {

    private String firstName;
    private String lastName;
    private String email;
    private String password;
}
