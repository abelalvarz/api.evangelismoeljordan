package com.evangelism.api.dto.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AttendanceDetailDto {
    private Integer activeMembers;
    private Integer activeChildren;
    private Integer inactiveMembers;
    private Integer inactiveChildren;
    private Integer visitorChildren;
    private Integer visitorAdults;
    private Integer totalAttendance;
}
