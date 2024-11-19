package com.simeon.lab3.services;

import com.simeon.lab3.History;
import com.simeon.lab3.dbcommunication.DatabaseHistory;
import com.simeon.lab3.dto.CheckResult;
import com.simeon.lab3.beans.SessionHistory;
import com.simeon.lab3.dto.AreaCheckRequest;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.inject.Any;
import jakarta.inject.Inject;

import java.time.LocalDateTime;

@ApplicationScoped
public class AreaCheckServiceImpl implements AreaCheckService {
    private History sessionHistory;
    private History databaseHistory;

    @Inject
    public void setHistory(@Any SessionHistory sessionHistory, DatabaseHistory databaseHistory) {
        this.sessionHistory = sessionHistory;
        this.databaseHistory = databaseHistory;
    }

    @Override
    public void handle(AreaCheckRequest request) {
        long start = System.currentTimeMillis();

        boolean hit = CheckUtil.check(request.x(), request.y(), request.r());

        long end = System.currentTimeMillis();
        long workingTime = end - start;

        CheckResult result = new CheckResult(request.x(), request.y(), request.r(), hit, workingTime, LocalDateTime.now());

        sessionHistory.addResult(result);
        databaseHistory.addResult(result);
    }
}
