package com.evangelism.api.dto.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class EvangelismDetailDto {

    private int vigilAttendance;
    private int homesVisited;
    private int newChristians;
    private int reconciled;
}
