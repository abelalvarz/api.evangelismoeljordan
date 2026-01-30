package com.evangelism.api.dto.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ReportExistenceResponse {
    private boolean exists;
}
