package com.evangelism.api.dto.request.ReportRequest;

import com.evangelism.api.dto.model.AttendanceDetailDto;
import com.evangelism.api.dto.model.EvangelismDetailDto;
import com.evangelism.api.dto.model.FinanceDetailDto;
import lombok.Data;

import java.time.LocalDate;
import java.util.UUID;

@Data
public class ReportRequest {

    private LocalDate meetingDate;
    private String hostName;
    private AttendanceDetailDto attendanceDetail;
    private EvangelismDetailDto evangelismDetail;
    private FinanceDetailDto financeDetail;
    private UUID cellId;
}
