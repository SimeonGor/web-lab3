package com.simeon.lab3.beans;

import com.simeon.lab3.dto.CheckResult;
import com.simeon.lab3.services.History;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class SessionHistoryTest {
    History history;

    @BeforeEach
    void setUp() {
        history = new SessionHistory();
    }

    @Test
    void checkAddResult() {
        CheckResult result = new CheckResult(BigDecimal.valueOf(0), BigDecimal.valueOf(0), BigDecimal.valueOf(0), false, 0, LocalDateTime.now());
        history.addResult(result);

        assertEquals(result, history.getResultList().get(0));
    }

    @Test
    void checkResultOrder() {
        CheckResult result0 = new CheckResult(BigDecimal.valueOf(0), BigDecimal.valueOf(0), BigDecimal.valueOf(0), false, 0, LocalDateTime.now());
        CheckResult result1 = new CheckResult(BigDecimal.valueOf(1), BigDecimal.valueOf(1), BigDecimal.valueOf(1), false, 0, LocalDateTime.now());
        history.addResult(result1);
        history.addResult(result0);

        assertEquals(result0, history.getResultList().get(0));
        assertEquals(result1, history.getResultList().get(1));
    }
}