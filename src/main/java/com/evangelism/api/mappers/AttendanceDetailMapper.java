package com.evangelism.api.mappers;

import com.evangelism.api.dto.model.AttendanceDetailDto;
import com.evangelism.api.entity.ReportAttendanceDetail;
import org.springframework.stereotype.Component;

@Component
public class AttendanceDetailMapper {

    public ReportAttendanceDetail toEntity(AttendanceDetailDto dto){
        return ReportAttendanceDetail.builder()
                .activeMembers(dto.getActiveMembers())
                .activeChildren(dto.getActiveChildren())
                .inactiveChildren(dto.getInactiveChildren())
                .inactiveMembers(dto.getInactiveMembers())
                .visitorAdults(dto.getVisitorAdults())
                .visitorChildren(dto.getVisitorChildren())
                .totalAttendance(dto.getTotalAttendance())
                .build();
    }
    public AttendanceDetailDto toDto(ReportAttendanceDetail entity){
        return AttendanceDetailDto.builder()
                .activeMembers(entity.getActiveMembers())
                .activeChildren(entity.getActiveChildren())
                .inactiveChildren(entity.getInactiveChildren())
                .inactiveMembers(entity.getInactiveMembers())
                .visitorAdults(entity.getVisitorAdults())
                .visitorChildren(entity.getVisitorChildren())
                .totalAttendance(entity.getTotalAttendance())
                .build();
    }
}
