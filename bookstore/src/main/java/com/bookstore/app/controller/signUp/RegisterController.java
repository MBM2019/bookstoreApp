package com.bookstore.app.controller.signUp;

import com.bookstore.app.domain.RegisterInputDto;
import com.bookstore.app.domain.UserDto;
import com.bookstore.app.mapper.DomainMapper;
import com.bookstore.app.service.auth.AuthService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

//TODO add annotation for validation
@RestController
@AllArgsConstructor
public class RegisterController {

    private final AuthService authService;
    private final DomainMapper domainMapper;

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody RegisterInputDto registerInputDto) {
        UserDto createdUser = domainMapper.toUserDto(authService.createUser(domainMapper.toSignUpInputBusiness(
                registerInputDto)));
        if (createdUser == null){
            //TODO create a proper exception
            return new ResponseEntity<>("User not created, come again later!", HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(createdUser, HttpStatus.CREATED);
    }
}
