package com.NeoCamp.Desafio_Futebol.controller;

import com.NeoCamp.Desafio_Futebol.dto.EstadioRequestDto;
import com.NeoCamp.Desafio_Futebol.dto.EstadioResponseDto;
import com.NeoCamp.Desafio_Futebol.service.EstadioService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/estadios")
public class EstadioController {

    private final EstadioService estadioService;

    public EstadioController(EstadioService estadioService) {
        this.estadioService = estadioService;
    }

    @GetMapping
    public ResponseEntity<Page<EstadioResponseDto>> findAll(Pageable pageable) {
        Page<EstadioResponseDto> estadios = estadioService.findAll(pageable);
        return ResponseEntity.status(HttpStatus.OK).body(estadios);
    }

    @GetMapping("/{id}")
    public ResponseEntity<EstadioResponseDto> findById(@PathVariable Long id) {
        EstadioResponseDto estadio = estadioService.findById(id);
        return ResponseEntity.status(HttpStatus.OK).body(estadio);
    }

    @PostMapping
    public ResponseEntity<EstadioResponseDto> create(@RequestBody @Valid EstadioRequestDto dto) {
        EstadioResponseDto estadio = estadioService.save(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(estadio);
    }

    @PutMapping("/{id}")
    public ResponseEntity<EstadioResponseDto> update(@PathVariable Long id, @RequestBody @Valid EstadioRequestDto dto) {
        EstadioResponseDto estadio = estadioService.update(id, dto);
        return ResponseEntity.status(HttpStatus.OK).body(estadio);
    }
}
