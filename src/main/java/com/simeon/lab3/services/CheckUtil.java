package com.simeon.lab3.services;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class CheckUtil {
    private CheckUtil() {
        throw new IllegalStateException("Utility class");
    }

    public static boolean check(BigDecimal x, BigDecimal y, BigDecimal r) {
        return checkFirstQuarter(x, y, r)
                || checkSecondQuarter(x, y, r)
                || checkThirdQuarter(x, y, r);
    }

    private static boolean checkFirstQuarter(BigDecimal x, BigDecimal y, BigDecimal r) {
        if (x.compareTo(BigDecimal.ZERO) < 0 || y.compareTo(BigDecimal.ZERO) < 0) return false;
        return (x.pow(2).add(y.pow(2)).compareTo(r.pow(2))) <= 0;
    }

    private static boolean checkSecondQuarter(BigDecimal x, BigDecimal y, BigDecimal r) {
        if (x.compareTo(BigDecimal.ZERO) > 0 || y.compareTo(BigDecimal.ZERO) < 0) return false;
        return x.multiply(BigDecimal.valueOf(2)).add(r).compareTo(y) <= 0;

    }

    private static boolean checkThirdQuarter(BigDecimal x, BigDecimal y, BigDecimal r) {
        if (x.compareTo(new BigDecimal("0")) > 0 || y.compareTo(new BigDecimal("0")) > 0) return false;
        return y.add(x).compareTo(r.negate()) >= 0 && x.compareTo(r.negate().divide(BigDecimal.valueOf(2), RoundingMode.UNNECESSARY)) <= 0;
    }
}
