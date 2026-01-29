package com.evangelism.api.entity;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Entity
@Table(name = "report_attendance_detail", schema = "evangelism")
@Getter
@Setter
@Builder
public class ReportAttendanceDetail {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @OneToOne
    @MapsId
    @JoinColumn(name = "report_id", nullable = false)
    private Report report;

    @Column(name = "active_members", nullable = false)
    private int activeMembers;

    @Column(name = "active_members_children", nullable = false)
    private int activeChildren;

    @Column(name = "inactive_members", nullable = false)
    private int inactiveMembers;

    @Column(name = "inactive_members_children", nullable = false)
    private int inactiveChildren;

    @Column(name = "children_visitors", nullable = false)
    private int visitorChildren;

    @Column(name = "adults_visitors", nullable = false)
    private int visitorAdults;

    @Column(name = "total_attendance", nullable = false)
    private int totalAttendance;

    public void calculateTotalAttendance() {
        this.totalAttendance =  activeMembers + activeChildren + inactiveMembers + inactiveChildren + visitorChildren + visitorAdults;
    }
}
