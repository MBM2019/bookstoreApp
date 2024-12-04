package com.bookstore.app.service.auth.impl;

import com.bookstore.app.business.RegisterInputBusiness;
import com.bookstore.app.business.UserBusiness;
import com.bookstore.app.entity.UserEntity;
import com.bookstore.app.exception.ExistingUsernameException;
import com.bookstore.app.mapper.BusinessMapper;
import com.bookstore.app.repository.UserRepository;
import com.bookstore.app.service.auth.AuthService;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;
    private final BusinessMapper businessMapper;

    @Override
    @Transactional
    public UserBusiness createUser(RegisterInputBusiness registerInputBusiness) {
        if (userRepository.findFirstByEmail(registerInputBusiness.getEmail()) != null) {
            throw new ExistingUsernameException(String.format("The username %s already exists",
                    registerInputBusiness.getEmail()));
        }
        UserEntity userEntity = new UserEntity();
        userEntity.setFirstName(registerInputBusiness.getFirstName());
        userEntity.setLastName(registerInputBusiness.getLastName());
        userEntity.setEmail(registerInputBusiness.getEmail());
        userEntity.setPassword(new BCryptPasswordEncoder().encode(registerInputBusiness.getPassword()));
        return businessMapper.toUserBusiness(userRepository.save(userEntity));
    }
}
