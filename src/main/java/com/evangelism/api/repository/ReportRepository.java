package com.evangelism.api.repository;

import com.evangelism.api.entity.Cell;
import com.evangelism.api.entity.Report;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Repository
public interface ReportRepository extends JpaRepository<Report, UUID> {

    boolean existsByCellAndMeetingDate(Cell cell, LocalDate meetingDate);
    boolean existsByCellAndMeetingDateBetween(Cell cell, LocalDate startDate, LocalDate endDate);
    List<Report> findAllByCell(Cell cell);
    List<Report> findAllByCellAndMeetingDateBetween(Cell cell, LocalDate startDate, LocalDate endDate);
    List<Report> findAllByMeetingDateBetween(LocalDate startDate, LocalDate endDate);
}
