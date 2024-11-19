package com.simeon.lab3.beans;

import com.simeon.lab3.qualifiers.HistoryBean;
import com.simeon.lab3.qualifiers.HistoryType;
import com.simeon.lab3.services.History;
import com.simeon.lab3.dto.CheckResult;
import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Named;

import java.io.Serial;
import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;


@SessionScoped
@Named(value = "history")
@HistoryBean(HistoryType.SESSION)
public class SessionHistory implements Serializable, History {
    @Serial
    private static final long serialVersionUID = 4078483457730741479L;

    private final List<CheckResult> resultList = new LinkedList<>();

    @Override
    public List<CheckResult> getResultList() {
        return resultList;
    }

    @Override
    public void addResult(CheckResult result) {
        resultList.add(0, result);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SessionHistory history = (SessionHistory) o;
        return Objects.equals(resultList, history.resultList);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(resultList);
    }

    @Override
    public String toString() {
        return "History{" +
                "resultList=" + resultList +
                '}';
    }
}
