package com.bookstore.app.service.impl;

import com.bookstore.app.business.CheckOutInputBusiness;
import com.bookstore.app.entity.CartItemEntity;
import com.bookstore.app.entity.CartShopEntity;
import com.bookstore.app.entity.UserEntity;
import com.bookstore.app.repository.BookRepository;
import com.bookstore.app.repository.CartItemRepository;
import com.bookstore.app.repository.CartShopRepository;
import com.bookstore.app.repository.UserRepository;
import com.bookstore.app.service.CheckOutService;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CheckOutServiceImpl implements CheckOutService {

    private final UserRepository userRepository;
    private final BookRepository bookRepository;
    private final CartItemRepository cartItemRepository;
    private final CartShopRepository cartShopRepository;

    @Override
    @Transactional
    public void checkout(CheckOutInputBusiness checkOutInputBusiness) {
        //Find user
        UserEntity userEntity = userRepository.findFirstByEmail(getUsername());
        //Create new CartShop
        CartShopEntity cartShopEntity = new CartShopEntity();
        cartShopEntity.setUsers(userEntity);
        cartShopEntity.setOrderPrice(checkOutInputBusiness.getOrderPrice());
        CartShopEntity cartShopEntitySaved = cartShopRepository.save(cartShopEntity);
        //Create new CartItems
        checkOutInputBusiness.getCartItemInputList().forEach(cartItemInputBusiness -> {
            CartItemEntity cartItemEntity = new CartItemEntity();
            cartItemEntity.setQuantity(cartItemInputBusiness.getQuantity());
            cartItemEntity.setBook(bookRepository.findById(cartItemInputBusiness.getBookId()).get());
            cartItemEntity.setCartShop(cartShopEntitySaved);
            cartItemRepository.save(cartItemEntity);
        });
    }

    private static String getUsername() {
        if (SecurityContextHolder.getContext() != null
                && SecurityContextHolder.getContext().getAuthentication() != null
                && SecurityContextHolder.getContext().getAuthentication().isAuthenticated()) {
            return SecurityContextHolder.getContext().getAuthentication().getName();
        }
        throw new UsernameNotFoundException("The user has not been found");
    }
}
