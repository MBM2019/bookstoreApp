package com.bookstore.app.controller.signUp;

import com.bookstore.app.domain.RegisterInputDto;
import com.bookstore.app.domain.UserDto;
import com.bookstore.app.exception.UserNotCreatedException;
import com.bookstore.app.mapper.DomainMapper;
import com.bookstore.app.service.auth.AuthService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Validated
@RestController
@AllArgsConstructor
public class RegisterController {

    private final AuthService authService;
    private final DomainMapper domainMapper;

    /**
     * Registration of new users
     * @param registerInputDto Object containing all the information needed in order to create a new user: firstName,
     *                         lastName, email, password.
     * @return A ResponseEntity containing the new user created
     */
    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody @Valid RegisterInputDto registerInputDto) {
        UserDto createdUser = domainMapper.toUserDto(authService.createUser(domainMapper.toSignUpInputBusiness(
                registerInputDto)));
        if (createdUser == null){
            throw new UserNotCreatedException("The user has not been created due some technical problems, try again " +
                    "later");
        }
        return new ResponseEntity<>(createdUser, HttpStatus.CREATED);
    }
}
