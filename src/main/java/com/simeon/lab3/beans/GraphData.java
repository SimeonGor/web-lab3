package com.simeon.lab3.beans;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Named;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;
import java.util.Objects;

@ApplicationScoped
@Named("graph")
public class GraphData implements Serializable {
    @Serial
    private static final long serialVersionUID = -787857483475381603L;
    private List<String> xScale = List.of("-5", "-4", "-3", "-2", "-1", "", "1", "2", "3", "4", "5");
    private List<String> yScale = List.of("5", "4", "3", "2", "1", "", "-1", "-2", "-3", "-4", "-5");

    public List<String> getXScale() {
        return xScale;
    }

    public void setXScale(List<String> xScale) {
        this.xScale = xScale;
    }

    public List<String> getYScale() {
        return yScale;
    }

    public void setYScale(List<String> yScale) {
        this.yScale = yScale;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GraphData graphData = (GraphData) o;
        return xScale == graphData.xScale && yScale == graphData.yScale;
    }

    @Override
    public int hashCode() {
        return Objects.hash(xScale, yScale);
    }

    @Override
    public String toString() {
        return "GraphData{" +
                "xScale=" + xScale +
                ", yScale=" + yScale +
                '}';
    }
}
