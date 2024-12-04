package com.bookstore.app.service.auth;

import com.bookstore.app.business.RegisterInputBusiness;
import com.bookstore.app.business.UserBusiness;

public interface AuthService {

    UserBusiness createUser(RegisterInputBusiness registerInputBusiness);
}
