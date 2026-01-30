package com.evangelism.api.dto.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class EvangelismDetailDto {

    private Integer vigilAttendance;
    private Integer homesVisited;
    private Integer newChristians;
    private Integer reconciled;
}
