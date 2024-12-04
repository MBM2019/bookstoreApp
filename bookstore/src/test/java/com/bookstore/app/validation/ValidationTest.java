package com.bookstore.app.validation;

import com.bookstore.app.domain.BookPageInputDto;
import com.bookstore.app.domain.CartItemInputDto;
import com.bookstore.app.domain.CheckOutInputDto;
import com.bookstore.app.domain.RegisterInputDto;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class ValidationTest {

    private Validator validator;

    @BeforeAll
    public void setUp() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    public void testBookPageInputDto() {
        BookPageInputDto bookPageInputDto = BookPageInputDto.builder()
                .pageNumber(new BigDecimal(1))
                .pageSize(new BigDecimal(5))
                .build();
        Set<ConstraintViolation<BookPageInputDto>> violations = validator.validate(bookPageInputDto);
        assertTrue(violations.isEmpty());

        //pageNumber null
        bookPageInputDto.setPageNumber(null);
        violations = validator.validate(bookPageInputDto);
        assertFalse(violations.isEmpty());
        assertEquals(violations.size(), 1);
        assertEquals(violations.iterator().next().getMessage(), "Page number can not be null");

        //pageNumber equals to 0
        bookPageInputDto.setPageNumber(BigDecimal.valueOf(0));
        violations = validator.validate(bookPageInputDto);
        assertFalse(violations.isEmpty());
        assertEquals(violations.size(), 1);
        assertEquals(violations.iterator().next().getMessage(), "Page number cannot be neither negative nor zero");

        //pageNumber lower than 0
        bookPageInputDto.setPageNumber(BigDecimal.valueOf(-1));
        violations = validator.validate(bookPageInputDto);
        assertFalse(violations.isEmpty());
        assertEquals(violations.size(), 1);
        assertEquals(violations.iterator().next().getMessage(), "Page number cannot be neither negative nor zero");

        //pageNumber higher than 3
        bookPageInputDto.setPageNumber(BigDecimal.valueOf(4));
        violations = validator.validate(bookPageInputDto);
        assertFalse(violations.isEmpty());
        assertEquals(violations.size(), 1);
        assertEquals(violations.iterator().next().getMessage(), "The maximum value for the page number is 3");

        //pageNumber containing 2 digits
        bookPageInputDto.setPageNumber(BigDecimal.valueOf(36));
        violations = validator.validate(bookPageInputDto);
        assertFalse(violations.isEmpty());
        assertEquals(violations.size(), 2);
        violations.forEach(violation -> {
            assertTrue(violation.getMessage().equals("Wrong format of page number: only 1 digit is " +
                    "allowed(not decimals)") || violation.getMessage().equals("The maximum value for the page number " +
                    "is 3"));
        });

        //pageNumber containing decimals
        bookPageInputDto.setPageNumber(BigDecimal.valueOf(1.6));
        violations = validator.validate(bookPageInputDto);
        assertFalse(violations.isEmpty());
        assertEquals(violations.size(), 1);
        assertEquals(violations.iterator().next().getMessage(), "Wrong format of page number: only 1 digit is " +
                "allowed(not decimals)");

        bookPageInputDto.setPageNumber(BigDecimal.valueOf(1));

        //pageSize null
        bookPageInputDto.setPageSize(null);
        violations = validator.validate(bookPageInputDto);
        assertFalse(violations.isEmpty());
        assertEquals(violations.size(), 1);
        assertEquals(violations.iterator().next().getMessage(), "Page size can not be null");

        //pageSize equals to zero
        bookPageInputDto.setPageSize(BigDecimal.valueOf(0));
        violations = validator.validate(bookPageInputDto);
        assertFalse(violations.isEmpty());
        assertEquals(violations.size(), 1);
        assertEquals(violations.iterator().next().getMessage(), "Page size cannot be neither negative nor zero");

        //pageSize lower than zero
        bookPageInputDto.setPageSize(BigDecimal.valueOf(-1));
        violations = validator.validate(bookPageInputDto);
        assertFalse(violations.isEmpty());
        assertEquals(violations.size(), 1);
        assertEquals(violations.iterator().next().getMessage(), "Page size cannot be neither negative nor zero");

        //pageSize higher than 10
        bookPageInputDto.setPageSize(BigDecimal.valueOf(12));
        violations = validator.validate(bookPageInputDto);
        assertFalse(violations.isEmpty());
        assertEquals(violations.size(), 1);
        assertEquals(violations.iterator().next().getMessage(), "The maximum value for the page size is 10");

        //pageSize containing more than 2 digits
        bookPageInputDto.setPageSize(BigDecimal.valueOf(129));
        violations = validator.validate(bookPageInputDto);
        assertFalse(violations.isEmpty());
        assertEquals(violations.size(), 2);
        violations.forEach(violation -> {
            assertTrue(violation.getMessage().equals("Wrong format of page size: only 2 digits are allowed" +
                    "(not decimals)") || violation.getMessage().equals("The maximum value for the page size is 10"));
        });

        //pageSize containing decimals
        bookPageInputDto.setPageSize(BigDecimal.valueOf(1,29));
        violations = validator.validate(bookPageInputDto);
        assertFalse(violations.isEmpty());
        assertEquals(violations.size(), 1);
        assertEquals(violations.iterator().next().getMessage(), "Wrong format of page size: only 2 digits " +
                "are allowed(not decimals)");
    }

    @Test
    public void testRegisterInputDto() {
        RegisterInputDto registerInputDto = RegisterInputDto.builder()
                .email("fred.milton@gmail.com")
                .firstName("Fred")
                .lastName("Milton")
                .password("Fredmil123%")
                .build();
        Set<ConstraintViolation<RegisterInputDto>> violations = validator.validate(registerInputDto);
        assertTrue(violations.isEmpty());

        //firstName null
        registerInputDto.setFirstName(null);
        violations = validator.validate(registerInputDto);
        assertFalse(violations.isEmpty());
        assertEquals(violations.size(), 1);
        assertEquals(violations.iterator().next().getMessage(), "Firstname can not be null, empty or blank");

        //firstName empty
        registerInputDto.setFirstName("");
        violations = validator.validate(registerInputDto);
        assertFalse(violations.isEmpty());
        assertEquals(violations.size(), 1);
        assertEquals(violations.iterator().next().getMessage(), "Firstname can not be null, empty or blank");

        //firstName blank
        registerInputDto.setFirstName(" ");
        violations = validator.validate(registerInputDto);
        assertFalse(violations.isEmpty());
        assertEquals(violations.size(), 1);
        assertEquals(violations.iterator().next().getMessage(), "Firstname can not be null, empty or blank");

        registerInputDto.setFirstName("Fred");

        //lastName null
        registerInputDto.setLastName(null);
        violations = validator.validate(registerInputDto);
        assertFalse(violations.isEmpty());
        assertEquals(violations.size(), 1);
        assertEquals(violations.iterator().next().getMessage(), "Lastname can not be null, empty or blank");

        //lastName empty
        registerInputDto.setLastName("");
        violations = validator.validate(registerInputDto);
        assertFalse(violations.isEmpty());
        assertEquals(violations.size(), 1);
        assertEquals(violations.iterator().next().getMessage(), "Lastname can not be null, empty or blank");

        //lastName blank
        registerInputDto.setLastName(" ");
        violations = validator.validate(registerInputDto);
        assertFalse(violations.isEmpty());
        assertEquals(violations.size(), 1);
        assertEquals(violations.iterator().next().getMessage(), "Lastname can not be null, empty or blank");

        registerInputDto.setLastName("Milton");

        //email null
        registerInputDto.setEmail(null);
        violations = validator.validate(registerInputDto);
        assertFalse(violations.isEmpty());
        assertEquals(violations.size(), 1);
        assertEquals(violations.iterator().next().getMessage(), "Email can not be null, empty or blank");

        //email empty
        registerInputDto.setEmail("");
        violations = validator.validate(registerInputDto);
        assertFalse(violations.isEmpty());
        assertEquals(violations.size(), 2);
        violations.forEach(violation -> {
            assertTrue(violation.getMessage().equals("Email can not be null, empty or blank")
                    || violation.getMessage().equals("Email is not valid"));
        });

        //email blank
        registerInputDto.setEmail(" ");
        violations = validator.validate(registerInputDto);
        assertFalse(violations.isEmpty());
        assertEquals(violations.size(), 2);
        violations.forEach(violation -> {
            assertTrue(violation.getMessage().equals("Email can not be null, empty or blank")
                    || violation.getMessage().equals("Email is not valid"));
        });

        //email wrong pattern
        registerInputDto.setEmail("1fred.milton@gmail.com");
        violations = validator.validate(registerInputDto);
        assertFalse(violations.isEmpty());
        assertEquals(violations.size(), 1);
        assertEquals(violations.iterator().next().getMessage(), "Email is not valid");

        //email wrong pattern
        registerInputDto.setEmail("fred.milton@gmail.com.");
        violations = validator.validate(registerInputDto);
        assertFalse(violations.isEmpty());
        assertEquals(violations.size(), 1);
        assertEquals(violations.iterator().next().getMessage(), "Email is not valid");

        //email wrong pattern
        registerInputDto.setEmail("fred.milton@gmail.com2");
        violations = validator.validate(registerInputDto);
        assertFalse(violations.isEmpty());
        assertEquals(violations.size(), 1);
        assertEquals(violations.iterator().next().getMessage(), "Email is not valid");

        //email wrong pattern
        registerInputDto.setEmail("fred..milton@gmail.com");
        violations = validator.validate(registerInputDto);
        assertFalse(violations.isEmpty());
        assertEquals(violations.size(), 1);
        assertEquals(violations.iterator().next().getMessage(), "Email is not valid");

        //email wrong pattern
        registerInputDto.setEmail("fred.miltongmail.com");
        violations = validator.validate(registerInputDto);
        assertFalse(violations.isEmpty());
        assertEquals(violations.size(), 1);
        assertEquals(violations.iterator().next().getMessage(), "Email is not valid");

        registerInputDto.setEmail("fred.milton@gmail.com");

        //password null
        registerInputDto.setPassword(null);
        violations = validator.validate(registerInputDto);
        assertFalse(violations.isEmpty());
        assertEquals(violations.size(), 1);
        assertEquals(violations.iterator().next().getMessage(), "Password can not be null, empty or blank");

        //password empty
        registerInputDto.setPassword("");
        violations = validator.validate(registerInputDto);
        assertFalse(violations.isEmpty());
        assertEquals(violations.size(), 2);
        violations.forEach(violation -> {
            assertTrue(violation.getMessage().equals("Password can not be null, empty or blank")
                    || violation.getMessage().equals("Password format is not valid"));
        });

        //password blank
        registerInputDto.setPassword(" ");
        violations = validator.validate(registerInputDto);
        assertFalse(violations.isEmpty());
        assertEquals(violations.size(), 2);
        violations.forEach(violation -> {
            assertTrue(violation.getMessage().equals("Password can not be null, empty or blank")
                    || violation.getMessage().equals("Password format is not valid"));
        });

        //password wrong pattern
        registerInputDto.setPassword("desfr123%gt");
        violations = validator.validate(registerInputDto);
        assertFalse(violations.isEmpty());
        assertEquals(violations.size(), 1);
        assertEquals(violations.iterator().next().getMessage(), "Password format is not valid");

        //password wrong pattern
        registerInputDto.setPassword("AWSDE123%KIU");
        violations = validator.validate(registerInputDto);
        assertFalse(violations.isEmpty());
        assertEquals(violations.size(), 1);
        assertEquals(violations.iterator().next().getMessage(), "Password format is not valid");

        //password wrong pattern
        registerInputDto.setPassword("AWSDEki%dews");
        violations = validator.validate(registerInputDto);
        assertFalse(violations.isEmpty());
        assertEquals(violations.size(), 1);
        assertEquals(violations.iterator().next().getMessage(), "Password format is not valid");

        //password wrong pattern
        registerInputDto.setPassword("A1%dews");
        violations = validator.validate(registerInputDto);
        assertFalse(violations.isEmpty());
        assertEquals(violations.size(), 1);
        assertEquals(violations.iterator().next().getMessage(), "Password format is not valid");

        //password wrong pattern
        registerInputDto.setPassword("A1%dews3245rfgtyuhyDEWS%%%12345");
        violations = validator.validate(registerInputDto);
        assertFalse(violations.isEmpty());
        assertEquals(violations.size(), 1);
        assertEquals(violations.iterator().next().getMessage(), "The maximum size for the password is 20 " +
                "characters");

        //password wrong pattern
        registerInputDto.setPassword("A13456ydews");
        violations = validator.validate(registerInputDto);
        assertFalse(violations.isEmpty());
        assertEquals(violations.size(), 1);
        assertEquals(violations.iterator().next().getMessage(), "Password format is not valid");
    }

    @Test
    public void testCartItemInputDto() {
        CartItemInputDto cartItemInputDto = CartItemInputDto.builder()
                .quantity(new BigDecimal(7))
                .bookId(UUID.randomUUID())
                .build();
        Set<ConstraintViolation<CartItemInputDto>> violations = validator.validate(cartItemInputDto);
        assertTrue(violations.isEmpty());

        //quantity null
        cartItemInputDto.setQuantity(null);
        violations = validator.validate(cartItemInputDto);
        assertEquals(violations.size(), 1);
        assertEquals(violations.iterator().next().getMessage(), "Quantity can not be null");

        //quantity lower than zero
        cartItemInputDto.setQuantity(BigDecimal.valueOf(-2));
        violations = validator.validate(cartItemInputDto);
        assertEquals(violations.size(), 1);
        assertEquals(violations.iterator().next().getMessage(), "Quantity cannot be neither negative nor zero");

        //quantity equals to zero
        cartItemInputDto.setQuantity(BigDecimal.valueOf(0));
        violations = validator.validate(cartItemInputDto);
        assertEquals(violations.size(), 1);
        assertEquals(violations.iterator().next().getMessage(), "Quantity cannot be neither negative nor zero");

        //quantity with 4 digits
        cartItemInputDto.setQuantity(BigDecimal.valueOf(6543));
        violations = validator.validate(cartItemInputDto);
        assertEquals(violations.size(), 1);
        assertEquals(violations.iterator().next().getMessage(), "Wrong format of quantity: only 3 digit " +
                "are allowed(not decimals)");

        //quantity with decimals
        cartItemInputDto.setQuantity(BigDecimal.valueOf(1.6));
        violations = validator.validate(cartItemInputDto);
        assertEquals(violations.size(), 1);
        assertEquals(violations.iterator().next().getMessage(), "Wrong format of quantity: only 3 digit " +
                "are allowed(not decimals)");

        cartItemInputDto.setQuantity(BigDecimal.valueOf(1));

        //bookId null
        cartItemInputDto.setBookId(null);
        violations = validator.validate(cartItemInputDto);
        assertEquals(violations.size(), 1);
        assertEquals(violations.iterator().next().getMessage(), "BookId can not be null");
    }

    @Test
    public void testCheckOutInputDto() {
        CheckOutInputDto checkOutInputDto = CheckOutInputDto.builder()
                .cartItemInputList(List.of(CartItemInputDto.builder()
                        .bookId(UUID.randomUUID())
                        .quantity(BigDecimal.valueOf(5))
                        .build()))
                .orderPrice(45.45)
                .build();
        Set<ConstraintViolation<CheckOutInputDto>> violations = validator.validate(checkOutInputDto);
        assertTrue(violations.isEmpty());

        //orderPrice equals to zero
        checkOutInputDto.setOrderPrice(0);
        violations = validator.validate(checkOutInputDto);
        assertEquals(violations.size(), 1);
        assertEquals(violations.iterator().next().getMessage(), "The price cannot be neither negative nor zero");

        //orderPrice lower than zero
        checkOutInputDto.setOrderPrice(-4.5);
        violations = validator.validate(checkOutInputDto);
        assertEquals(violations.size(), 1);
        assertEquals(violations.iterator().next().getMessage(), "The price cannot be neither negative nor zero");

        //orderPrice with 3 decimals
        checkOutInputDto.setOrderPrice(987654.356);
        violations = validator.validate(checkOutInputDto);
        assertEquals(violations.size(), 1);
        assertEquals(violations.iterator().next().getMessage(), "Wrong format of price");

        checkOutInputDto.setOrderPrice(987);

        //cartItemInputList empty
        checkOutInputDto.setCartItemInputList(new ArrayList<>());
        violations = validator.validate(checkOutInputDto);
        assertEquals(violations.size(), 1);
        assertEquals(violations.iterator().next().getMessage(), "The cartItemInputList cannot be empty");

    }
}
