package com.neocamp.soccer_matches.controller;

import com.neocamp.soccer_matches.dto.ChatResponse;
import com.neocamp.soccer_matches.dto.IaResponse;
import com.neocamp.soccer_matches.service.EstatisticasService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.tags.Tags;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequiredArgsConstructor
@RequestMapping("/estatisticas")
@Tags(
        @Tag(name = "Estatísticas", description = "Controlador para estatísticas de partidas"))
public class EstatisticasController {

     private final EstatisticasService estatisticasService;

     @Operation(summary = "Chaces de vitoria", description = "Obter as chances de vitorias entres dois times")
     @ApiResponse(responseCode = "200", description = "Chance retornada com sucesso",
             content = @Content(mediaType = "application/json",
                     schema = @Schema(implementation = IaResponse.class)))
     @ApiResponse(responseCode = "404", description = "Chances não calculadas")
     @GetMapping("/chance-vitoria/time-a/{teamNameA}/time-b/{teamNameB}")
     public ResponseEntity<ChatResponse> getChanceVitoria(@PathVariable("teamNameA") String teamNameA, @PathVariable("teamNameB") String teamNameB) throws IOException {
          return new ResponseEntity<>(estatisticasService.gerarEstatisticasTimes(teamNameA, teamNameB), HttpStatus.OK);
     }
}
