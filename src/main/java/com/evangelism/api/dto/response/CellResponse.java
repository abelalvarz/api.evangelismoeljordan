package com.evangelism.api.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import java.util.UUID;

@Data
@AllArgsConstructor
@Builder
public class CellResponse {
    private UUID id;
    private String name;
    private String teacherName;
}

