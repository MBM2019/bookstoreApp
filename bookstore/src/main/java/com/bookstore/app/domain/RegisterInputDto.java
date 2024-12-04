package com.bookstore.app.domain;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.FieldNameConstants;

@Data
@Builder
@FieldNameConstants
public class RegisterInputDto {

    @NotBlank(message = "Firstname can not be null, empty or blank")
    private String firstName;
    @NotBlank(message = "Lastname can not be null, empty or blank")
    private String lastName;
    @NotBlank(message = "Email can not be null, empty or blank")
    @Email(message = "Email is not valid", regexp = "^[a-zA-Z]+[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$")
    private String email;
    @NotBlank(message = "Password can not be null, empty or blank")
    @Pattern(message = "Password format is not valid", regexp = "^(?:(?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=]).*)[^\\s]{8,}$")
    private String password;

}
