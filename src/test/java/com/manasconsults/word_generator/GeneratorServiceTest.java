package com.manasconsults.word_generator;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

/**
 * This is a test class for GeneratorService.
 * This tests all the methods with different input types
 */

@SpringBootTest
class GeneratorServiceTest {

    @Autowired
    private GeneratorService service;

    @Test
    void testSingleDigits() throws Exception {
        assertEquals("NINE DOLLARS", service.convertNumberToWord(BigDecimal.valueOf(9.0)));
        assertEquals("NINE DOLLARS AND NINE CENTS", service.convertNumberToWord(BigDecimal.valueOf(9.9)));
        assertEquals("NINE DOLLARS AND NINE CENTS", service.convertNumberToWord(BigDecimal.valueOf(9.09)));
    }

    @Test
    void testDoubleDigits() throws Exception {
        assertEquals("NINETEEN DOLLARS", service.convertNumberToWord(BigDecimal.valueOf(19.0)));
        assertEquals("NINETY NINE DOLLARS AND NINE CENTS", service.convertNumberToWord(BigDecimal.valueOf(99.9)));
        assertEquals("TEN DOLLARS AND NINETY EIGHT CENTS", service.convertNumberToWord(BigDecimal.valueOf(10.98)));
    }

    @Test
    void testTripleDigits() throws Exception {
        assertEquals("ONE HUNDRED AND NINETY NINE DOLLARS AND NINE CENTS", service.convertNumberToWord(BigDecimal.valueOf(199.09)));
        assertEquals("NINE HUNDRED AND NINETY NINE DOLLARS AND NINETY NINE CENTS", service.convertNumberToWord(BigDecimal.valueOf(999.99)));
        assertEquals("ONE HUNDRED AND TWENTY DOLLARS", service.convertNumberToWord(BigDecimal.valueOf(120.0)));
    }

    @Test
    void testThousands() throws Exception {
        assertEquals("ONE THOUSAND AND NINE HUNDRED AND NINETY NINE DOLLARS AND NINE CENTS", service.convertNumberToWord(BigDecimal.valueOf(1999.09)));
        assertEquals("NINE THOUSAND AND NINE HUNDRED AND NINETY NINE DOLLARS AND NINETY NINE CENTS", service.convertNumberToWord(BigDecimal.valueOf(9999.99)));
        assertEquals("TWELVE THOUSAND DOLLARS", service.convertNumberToWord(BigDecimal.valueOf(12000.0)));
        assertEquals("ONE HUNDRED AND TWO THOUSAND AND FORTY DOLLARS AND FOUR CENTS", service.convertNumberToWord(BigDecimal.valueOf(102040.04)));
    }


}