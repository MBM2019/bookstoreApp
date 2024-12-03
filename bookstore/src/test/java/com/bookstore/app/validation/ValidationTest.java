package com.bookstore.app.validation;

import com.bookstore.app.domain.BookPageInputDto;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import java.math.BigDecimal;
import java.util.Set;

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
    public void testBookPageInputDto(){
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
}
