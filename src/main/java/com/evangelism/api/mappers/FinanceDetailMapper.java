package com.evangelism.api.mappers;

import com.evangelism.api.dto.model.FinanceDetailDto;
import com.evangelism.api.entity.ReportFinanceDetail;
import org.springframework.stereotype.Component;

@Component
public class FinanceDetailMapper {

    public ReportFinanceDetail toEntity(FinanceDetailDto dto){
        return ReportFinanceDetail.builder()
                .offeringAmount(dto.getOfferingAmount())
                .observations(dto.getObservations())
                .build();
    }

    public FinanceDetailDto toDto(ReportFinanceDetail entity){
        return FinanceDetailDto.builder()
                .offeringAmount(entity.getOfferingAmount())
                .observations(entity.getObservations())
                .build();
    }
}
