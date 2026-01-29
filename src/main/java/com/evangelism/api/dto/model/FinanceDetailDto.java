package com.evangelism.api.dto.model;

import jakarta.validation.constraints.PositiveOrZero;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Builder
public class FinanceDetailDto {
    @PositiveOrZero
    private BigDecimal offeringAmount;
    private String observations;
}
