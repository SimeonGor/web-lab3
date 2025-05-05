package com.simeon.lab3.services;

import com.simeon.lab3.beans.SessionHistory;
import com.simeon.lab3.dto.AreaCheckRequest;
import com.simeon.lab3.dto.CheckResult;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class AreaCheckServiceImplTest {
    History sessionHistory;
    History dbHistory;
    AreaCheckServiceImpl areaCheckService;

    @BeforeEach
    void setUp() {
        sessionHistory = new SessionHistory();
        dbHistory = new SessionHistory();

        areaCheckService = new AreaCheckServiceImpl();
        areaCheckService.setDatabaseHistory(sessionHistory);
        areaCheckService.setSessionHistory(dbHistory);
    }

    @Test
    void checkHandler() {
        AreaCheckRequest request = new AreaCheckRequest(BigDecimal.valueOf(0), BigDecimal.valueOf(0), BigDecimal.valueOf(2));

        CheckResult expectedResult = new CheckResult(request.x(), request.y(), request.r(),
                CheckUtil.check(request.x(), request.y(), request.r()),
                0, LocalDateTime.now());


        areaCheckService.handle(request);

        assertEquals(expectedResult.getX(), sessionHistory.getResultList().get(0).getX());
        assertEquals(expectedResult.getY(), sessionHistory.getResultList().get(0).getY());
        assertEquals(expectedResult.getR(), sessionHistory.getResultList().get(0).getR());
        assertEquals(expectedResult.isHit(), sessionHistory.getResultList().get(0).isHit());

        assertEquals(expectedResult.getX(), dbHistory.getResultList().get(0).getX());
        assertEquals(expectedResult.getY(), dbHistory.getResultList().get(0).getY());
        assertEquals(expectedResult.getR(), dbHistory.getResultList().get(0).getR());
        assertEquals(expectedResult.isHit(), dbHistory.getResultList().get(0).isHit());
    }

}