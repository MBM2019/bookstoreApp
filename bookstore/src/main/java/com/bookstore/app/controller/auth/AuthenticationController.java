package com.bookstore.app.controller.auth;

import com.bookstore.app.service.jwt.JwtTokenRequest;
import com.bookstore.app.service.jwt.JwtTokenResponse;
import com.bookstore.app.service.jwt.JwtTokenService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


@RestController
@AllArgsConstructor
public class AuthenticationController {

    private final JwtTokenService tokenService;
    private final AuthenticationManager authenticationManager;

    /**
     * Authentication of existing users
     * @param jwtTokenRequest Object containing the user credentials
     * @return A ResponseEntity object containing the token of the user.
     */
    @PostMapping("/authenticate")
    public ResponseEntity<JwtTokenResponse> generateToken(
            @RequestBody JwtTokenRequest jwtTokenRequest) {

        var authenticationToken =
                new UsernamePasswordAuthenticationToken(
                        jwtTokenRequest.email(),
                        jwtTokenRequest.password());
        var authentication =
                authenticationManager.authenticate(authenticationToken);
        var token = tokenService.generateToken(authentication);
        return ResponseEntity.ok(new JwtTokenResponse(token));
    }
}
