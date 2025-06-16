package com.NeoCamp.Desafio_Futebol.controller;

import com.NeoCamp.Desafio_Futebol.dto.ClubRequestDto;
import com.NeoCamp.Desafio_Futebol.dto.ClubResponseDto;
import com.NeoCamp.Desafio_Futebol.service.ClubService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/clubs")
public class ClubController {

    private final ClubService clubService;

    public ClubController(ClubService clubService) {
        this.clubService = clubService;
    }

    @GetMapping
    public ResponseEntity<Page<ClubResponseDto>> findAll(Pageable pageable) {
        Page<ClubResponseDto> clubs = clubService.findAll(pageable);
        return ResponseEntity.status(HttpStatus.OK).body(clubs);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ClubResponseDto> findById(@PathVariable Long id) {
        ClubResponseDto club = clubService.findById(id);
        return ResponseEntity.status(HttpStatus.OK).body(club);
    }

    @PostMapping
    public ResponseEntity<ClubResponseDto> create(@RequestBody @Valid ClubRequestDto dto) {
        ClubResponseDto club = clubService.save(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(club);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ClubResponseDto> update(@PathVariable Long id, @RequestBody @Valid ClubRequestDto dto) {
        ClubResponseDto club = clubService.update(id, dto);
        return ResponseEntity.status(HttpStatus.OK).body(club);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        clubService.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
