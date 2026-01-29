package com.evangelism.api.dto.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AttendanceDetailDto {
    private int activeMembers;
    private int activeChildren;
    private int inactiveMembers;
    private int inactiveChildren;
    private int visitorChildren;
    private int visitorAdults;
    private int totalAttendance;
}
