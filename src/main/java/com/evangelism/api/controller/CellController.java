package com.evangelism.api.controller;

import com.evangelism.api.converter.ResponseConverter;
import com.evangelism.api.dto.response.Response;
import com.evangelism.api.service.CellService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/api/cells")
@RequiredArgsConstructor
public class CellController {

    private final ResponseConverter responseConverter;
    private final CellService cellService;

    @GetMapping("/catalog")
    public ResponseEntity<Response> getAll(){
        return ResponseEntity.ok(
                responseConverter.convert(cellService.getAll())
        );
    }

    @GetMapping("/{cellId}")
    public ResponseEntity<Response> getById(@PathVariable UUID cellId){
        return ResponseEntity.ok(
                responseConverter.convert(cellService.getById(cellId))
        );
    }
}
