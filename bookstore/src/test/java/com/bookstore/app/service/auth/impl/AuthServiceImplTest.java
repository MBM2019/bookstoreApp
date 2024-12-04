package com.bookstore.app.service.auth.impl;

import com.bookstore.app.business.RegisterInputBusiness;
import com.bookstore.app.entity.UserEntity;
import com.bookstore.app.exception.ExistingUsernameException;
import com.bookstore.app.mapper.BusinessMapper;
import com.bookstore.app.mapper.BusinessMapperImpl;
import com.bookstore.app.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class AuthServiceImplTest {

    @Mock
    private UserRepository userRepository;
    private AuthServiceImpl authServiceImpl;

    @BeforeEach
    public void setup() {
        BusinessMapper businessMapper = new BusinessMapperImpl();
        authServiceImpl = new AuthServiceImpl(userRepository, businessMapper);
    }

    @Test
    public void createUserTest_ExceptionThrown() {
        RegisterInputBusiness registerInputBusiness = RegisterInputBusiness.builder()
                .email("fred.milton@gmail.com")
                .build();
        UserEntity userEntity = mock(UserEntity.class);
        when(userRepository.findFirstByEmail(anyString())).thenReturn(userEntity);
        assertThrows(ExistingUsernameException.class, () -> authServiceImpl.createUser(registerInputBusiness));
    }

    @Test
    public void createUserTest_NoExceptionThrown() {
        RegisterInputBusiness registerInputBusiness = RegisterInputBusiness.builder()
                .firstName("Fred")
                .lastName("Milton")
                .password("Fredmil123%")
                .email("fred.milton@gmail.com")
                .build();
        authServiceImpl.createUser(registerInputBusiness);
        verify(userRepository).save(any(UserEntity.class));
    }
}
