package com.evangelism.api.mappers;

import com.evangelism.api.dto.model.EvangelismDetailDto;
import com.evangelism.api.dto.response.ReportResponse;
import com.evangelism.api.dto.request.ReportRequest.ReportRequest;
import com.evangelism.api.entity.Cell;
import com.evangelism.api.entity.Report;
import com.evangelism.api.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class ReportMapper {

    private final CellMapper cellMapper;
    private final AttendanceDetailMapper attendanceDetailMapper;
    private final EvangelismDetailMapper evangelismDetailMapper;
    private final FinanceDetailMapper financeDetailMapper;

    public Report toEntity(ReportRequest request, Cell cell, User user){
        Report report = new Report();
        report.setCell(cell);
        report.setMeetingDate(request.getMeetingDate());
        report.setCreatedBy(user);
        report.setAttendanceDetail(attendanceDetailMapper.toEntity(
                request.getAttendanceDetail()
        ));
        report.setEvangelismDetail(evangelismDetailMapper.toEntity(
                request.getEvangelismDetail()
        ));
        report.setFinanceDetail(financeDetailMapper.toEntity(request.getFinanceDetail()));
        report.setHostName(request.getHostName());
        return report;
    }

    public ReportResponse toResponseDto(Report report){
        return ReportResponse.builder()
                .id(report.getId())
                .meetingDate(report.getMeetingDate())
                .hostName(report.getHostName())
                .attendanceDetail(attendanceDetailMapper.toDto(report.getAttendanceDetail()))
                .evangelismDetail(evangelismDetailMapper.toDto(report.getEvangelismDetail()))
                .financeDetail(financeDetailMapper.toDto(report.getFinanceDetail()))
                .cell(cellMapper.toCellResponse(report.getCell()))
                .build();
    }

    public List<ReportResponse> mapToReportReponseList(List<Report> reports){
        return reports.stream().map(this::toResponseDto).toList();
    }
}
