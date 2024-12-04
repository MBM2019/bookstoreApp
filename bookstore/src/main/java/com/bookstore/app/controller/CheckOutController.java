package com.bookstore.app.controller;

import com.bookstore.app.domain.CheckOutInputDto;
import com.bookstore.app.mapper.DomainMapper;
import com.bookstore.app.service.CheckOutService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Validated
@RestController
@RequestMapping("/checkout")
@AllArgsConstructor
public class CheckOutController {

    private final DomainMapper domainMapper;
    private final CheckOutService checkOutService;

    /**
     * Performing the checkout of a new order from an existing user. The process consists of
     * persisting the CartShop and CartItems registered by the user.
     * @param checkOutInputDto Object containing the total price of the CartShop and a list
     *                         of all the CartItems selected by the user.
     */
    @PostMapping
    public void checkout(@RequestBody @Valid CheckOutInputDto checkOutInputDto) {
        checkOutService.checkout(domainMapper.toCheckOutInputBusiness(checkOutInputDto));
    }
}
