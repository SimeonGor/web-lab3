package com.simeon.lab3.beans;

import com.simeon.lab3.dto.AreaCheckRequest;
import com.simeon.lab3.services.AreaCheckService;
import jakarta.enterprise.context.RequestScoped;
import jakarta.enterprise.inject.Any;
import jakarta.inject.Inject;
import jakarta.inject.Named;

import java.math.BigDecimal;

@RequestScoped
@Named("areaChecker")
public class AreaCheckerController {
    private BigDecimal x;
    private BigDecimal y;
    private BigDecimal r;

    private final AreaCheckService areaCheckerService;

    @Inject
    public AreaCheckerController(@Any AreaCheckService areaCheckService) {
        this.areaCheckerService = areaCheckService;
    }

    public void checkArea() {
        AreaCheckRequest request = new AreaCheckRequest(x, y, r);

        areaCheckerService.handle(request);

        clear();
    }

    private void clear() {
        x = null;
        y = null;
        r = null;
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
