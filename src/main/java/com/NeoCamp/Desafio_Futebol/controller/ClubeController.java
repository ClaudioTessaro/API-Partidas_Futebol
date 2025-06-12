package com.NeoCamp.Desafio_Futebol.controller;

import com.NeoCamp.Desafio_Futebol.dto.ClubeRequestDto;
import com.NeoCamp.Desafio_Futebol.dto.ClubeResponseDto;
import com.NeoCamp.Desafio_Futebol.service.ClubeService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/clubes")
public class ClubeController {

    private final ClubeService clubeService;

    public ClubeController(ClubeService clubeService) {
        this.clubeService = clubeService;
    }

    @GetMapping
    public Page<ClubeResponseDto>findAll(Pageable pageable) {
        return clubeService.findAll(pageable);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ClubeResponseDto> findById(@PathVariable Long id) {
        ClubeResponseDto clube = clubeService.findById(id);
        return ResponseEntity.status(HttpStatus.OK).body(clube);
    }

    @PostMapping
    public ResponseEntity<ClubeResponseDto> create(@RequestBody @Valid ClubeRequestDto dto) {
        ClubeResponseDto clube = clubeService.save(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(clube);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ClubeResponseDto> update(@PathVariable Long id, @RequestBody @Valid ClubeRequestDto dto) {
        ClubeResponseDto clube = clubeService.update(id, dto);
        return ResponseEntity.status(HttpStatus.OK).body(clube);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        clubeService.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
