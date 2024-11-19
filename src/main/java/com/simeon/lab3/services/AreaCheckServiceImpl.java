package com.simeon.lab3.services;

import com.simeon.lab3.dto.CheckResult;
import com.simeon.lab3.dto.AreaCheckRequest;
import com.simeon.lab3.qualifiers.HistoryBean;
import com.simeon.lab3.qualifiers.HistoryType;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import java.time.LocalDateTime;

@ApplicationScoped
public class AreaCheckServiceImpl implements AreaCheckService {
    private History sessionHistory;
    private History databaseHistory;

    @Inject
    public void setSessionHistory(@HistoryBean(HistoryType.SESSION)History sessionHistory) {
        this.sessionHistory = sessionHistory;
    }

    @Inject
    public void setDatabaseHistory(@HistoryBean(HistoryType.DATABASE) History databaseHistory) {
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
