package com.evangelism.api.mappers;

import com.evangelism.api.dto.model.EvangelismDetailDto;
import com.evangelism.api.entity.ReportEvangelismDetail;
import org.springframework.stereotype.Component;

@Component
public class EvangelismDetailMapper {

    public ReportEvangelismDetail toEntity(EvangelismDetailDto dto){
        return ReportEvangelismDetail.builder()
                .vigilAttendance(dto.getVigilAttendance())
                .homesVisited(dto.getHomesVisited())
                .newChristians(dto.getNewChristians())
                .reconciled(dto.getReconciled())
                .build();
    }
    public EvangelismDetailDto toDto(ReportEvangelismDetail entity){
        return EvangelismDetailDto.builder()
                .vigilAttendance(entity.getVigilAttendance())
                .homesVisited(entity.getHomesVisited())
                .newChristians(entity.getNewChristians())
                .reconciled(entity.getReconciled())
                .build();
    }
}
