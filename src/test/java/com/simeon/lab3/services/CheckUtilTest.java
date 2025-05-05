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

    @ParameterizedTest
    @ValueSource(strings = {"2", "3", "4", "5"})
    void checkFirstQuarterHit(String string_r) {
        BigDecimal r = new BigDecimal(string_r);
        BigDecimal x = r.divide(BigDecimal.valueOf(2), RoundingMode.HALF_DOWN);
        BigDecimal y = x;

        assertTrue(CheckUtil.check(x, y, r));
    }

    @ParameterizedTest
    @ValueSource(strings = {"2", "3", "4", "5"})
    void checkFirstQuarterMiss(String string_r) {
        BigDecimal r = new BigDecimal(string_r);
        BigDecimal x = r.multiply(BigDecimal.valueOf(2));
        BigDecimal y = x;

        assertFalse(CheckUtil.check(x, y, r));
    }

    @ParameterizedTest
    @ValueSource(strings = {"2", "3", "4", "5"})
    void checkSecondQuarterBorderHit(String string_r) {
        BigDecimal r = new BigDecimal(string_r);
        BigDecimal x = r.divide(BigDecimal.valueOf(-4), RoundingMode.DOWN);
        BigDecimal y = x.multiply(BigDecimal.valueOf(-2));

        assertTrue(CheckUtil.check(x, y, r));
    }

    @ParameterizedTest
    @ValueSource(strings = {"2", "3", "4", "5"})
    void checkSecondQuarterHit(String string_r) {
        BigDecimal r = new BigDecimal(string_r);
        BigDecimal x = r.divide(BigDecimal.valueOf(-8), RoundingMode.HALF_DOWN);
        BigDecimal y = x.multiply(BigDecimal.valueOf(-2));

        assertTrue(CheckUtil.check(x, y, r));
    }

    @ParameterizedTest
    @ValueSource(strings = {"2", "3", "4", "5"})
    void checkSecondQuarterMiss(String string_r) {
        BigDecimal r = new BigDecimal(string_r);
        BigDecimal x = r.divide(BigDecimal.valueOf(-2), RoundingMode.HALF_DOWN);
        BigDecimal y = x.multiply(BigDecimal.valueOf(-2));

        assertFalse(CheckUtil.check(x, y, r));
    }

    @ParameterizedTest
    @ValueSource(strings = {"2", "3", "4", "5"})
    void checkThirdQuarterBorderHit(String string_r) {
        BigDecimal r = new BigDecimal(string_r);
        BigDecimal x = r.divide(BigDecimal.valueOf(-2), RoundingMode.DOWN);
        BigDecimal y = x.multiply(BigDecimal.valueOf(2));

        assertTrue(CheckUtil.check(x, y, r));
    }

    @ParameterizedTest
    @ValueSource(strings = {"2", "3", "4", "5"})
    void checkThirdQuarterHit(String string_r) {
        BigDecimal r = new BigDecimal(string_r);
        BigDecimal x = r.divide(BigDecimal.valueOf(-4), RoundingMode.HALF_DOWN);
        BigDecimal y = x.multiply(BigDecimal.valueOf(2));

        assertTrue(CheckUtil.check(x, y, r));
    }

    @ParameterizedTest
    @ValueSource(strings = {"2", "3", "4", "5"})
    void checkThirdQuarterMiss(String string_r) {
        BigDecimal r = new BigDecimal(string_r);
        BigDecimal x = r.negate();
        BigDecimal y = x;

        assertFalse(CheckUtil.check(x, y, r));
    }

    @ParameterizedTest
    @ValueSource(strings = {"2", "3", "4", "5"})
    void checkForthQuarterMiss(String string_r) {
        BigDecimal r = new BigDecimal(string_r);
        BigDecimal x = r;
        BigDecimal y = x.negate();

        assertFalse(CheckUtil.check(x, y, r));
    }
}