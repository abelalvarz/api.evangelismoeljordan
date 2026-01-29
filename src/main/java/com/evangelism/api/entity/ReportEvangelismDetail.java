package com.evangelism.api.entity;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Entity
@Table(name = "report_evangelism_detail", schema="evangelism")
@Getter
@Setter
@Builder
public class ReportEvangelismDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @OneToOne
    @MapsId
    @JoinColumn(name = "report_id", nullable = false)
    private Report report;

    @Column(name = "vigil_attendance", nullable = false)
    private int vigilAttendance;

    @Column(name = "visited_homes", nullable = false)
    private int homesVisited;

    @Column(name = "new_christian", nullable = false)
    private int newChristians;

    @Column(name = "reconciled_people", nullable = false)
    private int reconciled;
}

