package com.simeon.lab3;

import com.simeon.lab3.dto.CheckResult;

import java.util.List;

public interface History {
    List<CheckResult> getResultList();

    void addResult(CheckResult result);
}
