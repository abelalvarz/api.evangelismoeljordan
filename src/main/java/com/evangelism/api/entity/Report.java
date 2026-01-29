package com.evangelism.api.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

@Entity
@Table(name = "report", schema = "evangelism")
@Getter
@Setter
public class Report {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "meeting_date", nullable = false)
    private LocalDate meetingDate;

    @Column(name = "host_name")
    private String hostName;

    @ManyToOne
    @JoinColumn(name = "cell_id", nullable = false)
    private Cell cell;

    @OneToOne(mappedBy = "report", cascade = CascadeType.ALL)
    @PrimaryKeyJoinColumn
    private ReportAttendanceDetail attendanceDetail;

    @OneToOne(mappedBy = "report", cascade = CascadeType.ALL)
    @PrimaryKeyJoinColumn
    private ReportEvangelismDetail evangelismDetail;

    @OneToOne(mappedBy = "report", cascade = CascadeType.ALL)
    @PrimaryKeyJoinColumn
    private ReportFinanceDetail financeDetail;

    @ManyToOne
    @JoinColumn(name = "created_by", nullable = false)
    private User createdBy;

    @PrePersist
    @PreUpdate
    public void validateAndCalculate() {
        if (this.attendanceDetail != null) {
            this.attendanceDetail.calculateTotalAttendance();
        }
    }
}