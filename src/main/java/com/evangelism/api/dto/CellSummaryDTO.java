package com.evangelism.api.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import java.util.UUID;

@Data
@AllArgsConstructor
@Builder
public class CellSummaryDTO {
    private UUID id;
    private String name;
    private String teacherName;
}

