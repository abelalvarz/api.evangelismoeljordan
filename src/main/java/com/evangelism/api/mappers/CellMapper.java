package com.evangelism.api.mappers;

import com.evangelism.api.dto.response.CellResponse;
import com.evangelism.api.entity.Cell;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CellMapper {

    public CellResponse toCellResponse(Cell cell){
        return CellResponse.builder()
                .id(cell.getId())
                .name(cell.getName())
                .teacherName(cell.getTeacherName())
                .build();
    }
    public List<CellResponse> toCellResponseList(List<Cell> cells){
        return cells.stream().map(this::toCellResponse).toList();
    }
}
