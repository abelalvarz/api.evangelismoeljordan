package com.evangelism.api.dto.model;

import jakarta.validation.constraints.PositiveOrZero;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class FinanceDetailDto {
    @PositiveOrZero
    private BigDecimal offeringAmount;
    private String observations;
}
