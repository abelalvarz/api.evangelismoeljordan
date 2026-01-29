package com.evangelism.api.mappers;

import com.evangelism.api.dto.CellSummaryDTO;
import com.evangelism.api.entity.Cell;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CellMapper {

    public CellSummaryDTO toCellResponse(Cell cell){
        return CellSummaryDTO.builder()
                .id(cell.getId())
                .name(cell.getName())
                .teacherName(cell.getTeacherName())
                .build();
    }
    public List<CellSummaryDTO> toCellResponseList(List<Cell> cells){
        return cells.stream().map(this::toCellResponse).toList();
    }
}
