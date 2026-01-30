package com.evangelism.api.entity;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.util.UUID;

@Entity
@Table(name = "report_finance_detail", schema = "evangelism")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
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
