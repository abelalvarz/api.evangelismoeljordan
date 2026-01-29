package com.evangelism.api.controller;

import com.evangelism.api.converter.ResponseConverter;
import com.evangelism.api.dto.Response;
import com.evangelism.api.dto.request.ReportRequest.ReportRequest;
import com.evangelism.api.service.ReportService;
import com.evangelism.api.security.CustomUserDetails;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.core.annotation.AuthenticationPrincipal;

import java.time.LocalDate;
import java.util.UUID;

@RestController
@RequestMapping("/api/reports")
@RequiredArgsConstructor
public class ReportController {

    private final ReportService reportService;

    private final ResponseConverter responseConverter;

    @PostMapping
    public ResponseEntity<Response> create(@RequestBody ReportRequest request,
                                         @AuthenticationPrincipal CustomUserDetails userDetails) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(responseConverter.convert(
                        reportService.createReport(userDetails.getUser().getId(), request)
                ));
    }

    @GetMapping
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<Response> getAllReports(@RequestParam("startDate") LocalDate startDate,
                                                  @RequestParam("endDate") LocalDate endDate) {
        return ResponseEntity.ok(
                responseConverter.convert(reportService.findAll(startDate, endDate)
                ));
    }

    @GetMapping("/{reportId}")
    public ResponseEntity<Response> getByReportId(@PathVariable UUID reportId){
        return ResponseEntity.ok(
                responseConverter.convert(reportService.findById(reportId)
        ));
    }

    @GetMapping("/my-cell")
    public ResponseEntity<Response> getMyCellReports(@AuthenticationPrincipal CustomUserDetails userDetails) {
        return ResponseEntity.ok(
                responseConverter.convert(reportService.findByUserCell(userDetails.getUser().getId())
        ));
    }
    @GetMapping("/my-cell/f")
    public ResponseEntity<Response> getWeeklyReportIfExists(@AuthenticationPrincipal CustomUserDetails userDetails,
                                                               @RequestParam("startDate") LocalDate startDate,
                                                               @RequestParam("endDate") LocalDate endDate) {
        UUID userId = userDetails.getUser().getId();
        return ResponseEntity.ok(
                responseConverter.convert(reportService.getWeeklyReportIfExists(userId, startDate, endDate)
                ));
    }

    @GetMapping("/exists")
    public ResponseEntity<Response> validateExistsOneBetweenDateAndCellId(@RequestParam("cellId") UUID cellId,
                                                  @RequestParam("startDate") LocalDate startDate,
                                                  @RequestParam("endDate") LocalDate endDate) {
        return ResponseEntity.ok(
                responseConverter.convert(reportService.findExistsReport(cellId,startDate, endDate)
                ));
    }

    @DeleteMapping("/{reportId}")
    public ResponseEntity<Response> deleteReport(@PathVariable UUID reportId){
        reportService.deleteReport(reportId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT)
                .body(responseConverter.convert(null));
    }
}
