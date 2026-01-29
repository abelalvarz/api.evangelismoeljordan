package com.evangelism.api.dto.response;

import com.evangelism.api.dto.CellSummaryDTO;
import com.evangelism.api.dto.model.AttendanceDetailDto;
import com.evangelism.api.dto.model.EvangelismDetailDto;
import com.evangelism.api.dto.model.FinanceDetailDto;
import com.evangelism.api.entity.ReportAttendanceDetail;
import com.evangelism.api.entity.ReportEvangelismDetail;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

@Data
@AllArgsConstructor
@Builder
public class ReportResponse {
    private UUID id;
    private LocalDate meetingDate;
    private String hostName;
    private AttendanceDetailDto attendanceDetail;
    private EvangelismDetailDto evangelismDetail;
    private FinanceDetailDto financeDetail;
    private String submittedBy;
    private CellSummaryDTO cell;
}

