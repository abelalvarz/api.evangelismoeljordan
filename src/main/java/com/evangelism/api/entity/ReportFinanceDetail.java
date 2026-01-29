package com.evangelism.api.entity;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.UUID;

@Entity
@Table(name = "report_finance_detail", schema = "evangelism")
@Getter
@Setter
@Builder
public class ReportFinanceDetail {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @OneToOne
    @MapsId
    @JoinColumn(name = "report_id")
    private Report report;

    @Column(name = "offering_amount")
    private BigDecimal offeringAmount;

    @Column(name = "observations")
    private String observations;

}
