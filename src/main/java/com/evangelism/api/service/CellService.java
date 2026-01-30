package com.evangelism.api.service;

import com.evangelism.api.dto.response.CellResponse;
import com.evangelism.api.entity.Cell;
import com.evangelism.api.entity.Role;
import com.evangelism.api.entity.User;
import com.evangelism.api.exceptions.ResourceNotFoundException;
import com.evangelism.api.mappers.CellMapper;
import com.evangelism.api.repository.CellRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CellService {

    private final CellRepository cellRepository;
    private final CellMapper cellMapper;

    public List<CellResponse> getAll(){
        List<Cell> cells = findAll();
        return cellMapper.toCellResponseList(cells);
    }

    public CellResponse getById(UUID cellId){
        Cell cell = findById(cellId);
        return cellMapper.toCellResponse(cell);
    }

    public CellResponse findCellByUserAndRole (User user){
        if (user.getRoles().contains(Role.ADMIN)) return null;

        return cellRepository.findByTeacherOrSecretary(user, user)
                .map(cell -> new CellResponse(cell.getId(), cell.getName(), cell.getTeacher().getFullName()))
                .orElse(null);
    }

    public Cell findCellByUser(User user){
        return cellRepository.findByTeacherOrSecretary(user, user)
                .orElseThrow(() -> new ResourceNotFoundException("El usuario no tiene una célula asignada."));
    }

    public Cell findById(UUID id){
        return cellRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("No existe la célula especificada."));
    }
    public List<Cell> findAll(){
        return cellRepository.findAll();
    }
}
