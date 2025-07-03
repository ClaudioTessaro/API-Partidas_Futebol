package com.NeoCamp.soccer_matches.controller;

import com.NeoCamp.soccer_matches.dto.IaResponse;
import com.NeoCamp.soccer_matches.service.EstatisticasService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/estatisticas")
public class EstatisticasController {

     private final EstatisticasService estatisticasService;

     @GetMapping("/chance-vitoria/time-a/{teamNameA}/time-b/{teamNameB}")
     public ResponseEntity<IaResponse> getChanceVitoria(@PathVariable("teamNameA") String teamNameA, @PathVariable("teamNameB") String teamNameB) {
          return new ResponseEntity<>(estatisticasService.gerarEstatisticasTimes(teamNameA, teamNameB), HttpStatus.OK);
     }
}
