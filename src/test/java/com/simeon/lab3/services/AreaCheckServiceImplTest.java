package com.simeon.lab3.services;

import com.simeon.lab3.dto.AreaCheckRequest;
import com.simeon.lab3.dto.CheckResult;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class AreaCheckServiceImplTest {
    @Mock
    History sessionHistory;
    @Mock
    History dbHistory;
    AreaCheckServiceImpl areaCheckService;

    @BeforeEach
    void setUp() {
        areaCheckService = new AreaCheckServiceImpl();
        areaCheckService.setSessionHistory(sessionHistory);
        areaCheckService.setDatabaseHistory(dbHistory);
    }


    @Test
    void checkHandler() {
        AreaCheckRequest request = new AreaCheckRequest(BigDecimal.valueOf(0), BigDecimal.valueOf(0), BigDecimal.valueOf(2));

        CheckResult expectedResult = new CheckResult(request.x(), request.y(), request.r(),
                CheckUtil.check(request.x(), request.y(), request.r()),
                0, LocalDateTime.now());


        areaCheckService.handle(request);

        ArgumentCaptor<CheckResult> captor = ArgumentCaptor.forClass(CheckResult.class);
        Mockito.verify(sessionHistory).addResult(captor.capture());

        CheckResult capturedResult = captor.getValue();
        assertEquals(request.x(), capturedResult.getX());
        assertEquals(request.y(), capturedResult.getY());
        assertEquals(request.r(), capturedResult.getR());
        assertEquals(expectedResult.isHit(), capturedResult.isHit());

        Mockito.verify(dbHistory).addResult(captor.capture()); // Захватываем аргумент

        capturedResult = captor.getValue();
        assertEquals(request.x(), capturedResult.getX());
        assertEquals(request.y(), capturedResult.getY());
        assertEquals(request.r(), capturedResult.getR());
        assertEquals(expectedResult.isHit(), capturedResult.isHit());
    }

}