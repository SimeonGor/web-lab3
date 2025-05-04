package com.simeon.lab3.services;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.math.BigDecimal;
import java.math.RoundingMode;

import static org.junit.jupiter.api.Assertions.*;

class CheckUtilTest {

    @ParameterizedTest
    @ValueSource(strings = {"2", "3", "4", "5"})
    void checkFirstQuarterBorderHit(String string_r) {
        BigDecimal r = new BigDecimal(string_r);
        BigDecimal x = r.divide(BigDecimal.valueOf(Math.sqrt(2)), RoundingMode.FLOOR);
        BigDecimal y = x;

        assertTrue(CheckUtil.check(x, y, r));
    }
}