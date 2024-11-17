package com.simeon.lab3.beans;

import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Named;

import java.math.BigDecimal;

@RequestScoped
@Named("areaChecker")
public class AreaChecker {
    private BigDecimal x;
    private BigDecimal y;
    private BigDecimal r;

    public AreaChecker() {

    }

    public void checkArea() {

    }

    public BigDecimal getY() {
        return y;
    }

    public void setY(BigDecimal y) {
        this.y = y;
    }

    public BigDecimal getR() {
        return r;
    }

    public void setR(BigDecimal r) {
        this.r = r;
    }

    public BigDecimal getX() {
        return x;
    }

    public void setX(BigDecimal x) {
        this.x = x;
    }
}
