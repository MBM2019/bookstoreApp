package com.bookstore.app.service.jwt;

import com.bookstore.app.entity.UserEntity;
import com.bookstore.app.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class UserDetailsServiceImplTest {

    @Mock
    private UserRepository userRepository;
    private UserDetailsServiceImpl userDetailsServiceimpl;

    @BeforeEach
    public void setup() {
        userDetailsServiceimpl = new UserDetailsServiceImpl(userRepository);
    }

    @Test
    public void loadUserByUsernameTest_ExceptionThrown() {
        assertThrows(UsernameNotFoundException.class, () -> userDetailsServiceimpl.loadUserByUsername(
                "fred.milton@gmail.com"));
    }

    @Test
    public void loadUserByUsernameTest_NoExceptionThrown() {
        UserEntity userEntity = mock(UserEntity.class);
        when(userRepository.findFirstByEmail(anyString())).thenReturn(userEntity);
        when(userEntity.getEmail()).thenReturn("fred.milton@gmail.com");
        when(userEntity.getPassword()).thenReturn("Fredmil123%");
        UserDetails userDetails = userDetailsServiceimpl.loadUserByUsername("fred.milton@gmail.com");
        assertNotNull(userDetails);
        assertNotNull(userDetails.getUsername());
        assertEquals(userDetails.getUsername(), userEntity.getEmail());
        assertNotNull(userDetails.getPassword());
        assertEquals(userDetails.getPassword(), userEntity.getPassword());
    }
}
