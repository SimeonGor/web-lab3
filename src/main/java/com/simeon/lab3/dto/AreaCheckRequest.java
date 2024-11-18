package com.simeon.lab3.dto;

import java.math.BigDecimal;

public record AreaCheckRequest(BigDecimal x, BigDecimal y, BigDecimal r) {
}
