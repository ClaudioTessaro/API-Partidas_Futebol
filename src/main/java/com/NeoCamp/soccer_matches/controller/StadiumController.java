package com.NeoCamp.soccer_matches.controller;

import com.NeoCamp.soccer_matches.dto.StadiumRequestDto;
import com.NeoCamp.soccer_matches.dto.StadiumResponseDto;
import com.NeoCamp.soccer_matches.service.StadiumService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/stadiums")
public class StadiumController {

    private final StadiumService stadiumService;

    public StadiumController(StadiumService stadiumService) {
        this.stadiumService = stadiumService;
    }

    @GetMapping
    public ResponseEntity<Page<StadiumResponseDto>> findAll(Pageable pageable) {
        Page<StadiumResponseDto> stadiums = stadiumService.findAll(pageable);
        return ResponseEntity.status(HttpStatus.OK).body(stadiums);
    }

    @GetMapping("/{id}")
    public ResponseEntity<StadiumResponseDto> findById(@PathVariable Long id) {
        StadiumResponseDto stadium = stadiumService.findById(id);
        return ResponseEntity.status(HttpStatus.OK).body(stadium);
    }

    @PostMapping
    public ResponseEntity<StadiumResponseDto> create(@RequestBody @Valid StadiumRequestDto dto) {
        StadiumResponseDto stadium = stadiumService.save(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(stadium);
    }

    @PutMapping("/{id}")
    public ResponseEntity<StadiumResponseDto> update(@PathVariable Long id, @RequestBody @Valid StadiumRequestDto dto) {
        StadiumResponseDto stadium = stadiumService.update(id, dto);
        return ResponseEntity.status(HttpStatus.OK).body(stadium);
    }
}
