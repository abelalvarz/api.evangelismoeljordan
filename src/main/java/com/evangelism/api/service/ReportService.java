package com.evangelism.api.service;

import com.evangelism.api.dto.response.ReportExistenceResponse;
import com.evangelism.api.dto.response.ReportResponse;
import com.evangelism.api.dto.request.ReportRequest.ReportRequest;
import com.evangelism.api.entity.Cell;
import com.evangelism.api.entity.Report;
import com.evangelism.api.entity.User;
import com.evangelism.api.exceptions.ResourceNotFoundException;
import com.evangelism.api.mappers.ReportMapper;
import com.evangelism.api.repository.ReportRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ReportService {

    private final ReportRepository reportRepository;
    private final CellService cellService;
    private final UserService userService;
    private final ReportMapper reportMapper;

    @Transactional
    public ReportResponse createReport(UUID userId, ReportRequest request) {
        User user = userService.findById(userId);

        Cell cell = determineCellForReport(user, request.getCellId());

        validateUniqueReport(cell, request.getMeetingDate());

        Report report = reportMapper.toEntity(request,cell,user);
        return reportMapper.toResponseDto(reportRepository.save(report));
    }

    public List<ReportResponse> findAll(LocalDate startDate, LocalDate endDate){
        List<Report> reports = reportRepository.findAllByMeetingDateBetween(startDate, endDate);
        return reportMapper.mapToReportReponseList(reports);
    }

    public ReportResponse findById(UUID reportId){
        Report report = reportRepository.findById(reportId)
                .orElseThrow(()->new ResourceNotFoundException("Report not found with id: "+reportId));
        return reportMapper.toResponseDto(report);
    }

    public List<ReportResponse> findByUserCell(UUID userId, LocalDate startDate, LocalDate endDate) {
        User user = userService.findById(userId);
        Cell cell = cellService.findCellByUser(user);
        List<Report> reports = reportRepository.findAllByCellAndMeetingDateBetween(cell, startDate, endDate);
        return reportMapper.mapToReportReponseList(reports);
    }

    public ReportExistenceResponse validateReportExistence(UUID cellId, LocalDate startDate, LocalDate endDate){
        Cell cell = cellService.findById(cellId);
        boolean exists = reportRepository.existsByCellAndMeetingDateBetween(cell, startDate, endDate);
        return ReportExistenceResponse.builder()
                .exists(exists)
                .build();
    }

    public void deleteReport(UUID reportId){
        reportRepository.deleteById(reportId);
    }

    private Cell determineCellForReport(User user, UUID requestedCellId) {
        if (user.isAdmin() && requestedCellId != null) {
            return cellService.findById(requestedCellId);
        }
        return cellService.findCellByUser(user);
    }

    private void validateUniqueReport(Cell cell, LocalDate meetingDate) {
        if (reportRepository.existsByCellAndMeetingDate(cell, meetingDate)) {
            throw new RuntimeException("Ya existe un reporte para esta fecha: " + meetingDate);
        }
    }
}