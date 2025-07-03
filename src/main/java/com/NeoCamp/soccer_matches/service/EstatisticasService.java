package com.neocamp.soccer_matches.service;

import com.neocamp.soccer_matches.dto.ChatResponse;
import com.neocamp.soccer_matches.dto.IaResponse;
import com.neocamp.soccer_matches.dto.club.ClubStatsResponseDto;
import com.neocamp.soccer_matches.dto.util.PromptUtil;
import com.neocamp.soccer_matches.enums.MatchFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
@RequiredArgsConstructor
public class EstatisticasService {

    private final IaService iaService;
    private final ClubService clubService;

    public ChatResponse gerarEstatisticasTimes(String teamNameA, String teamNameB) throws IOException {
        ClubStatsResponseDto teamAHome = clubService.getClubStats(teamNameA, MatchFilter.HOME);
        ClubStatsResponseDto teamAWay = clubService.getClubStats(teamNameA, MatchFilter.AWAY);
        ClubStatsResponseDto teamBHome = clubService.getClubStats(teamNameB, MatchFilter.HOME);
        ClubStatsResponseDto teamBAway = clubService.getClubStats(teamNameB, MatchFilter.AWAY);
        String prompt = PromptUtil.getPrompt(teamNameA, teamNameB, teamAHome.getTotalWins().toString(), teamAWay.getTotalWins().toString(),
                teamBHome.getTotalWins().toString(), teamBAway.getTotalWins().toString(), teamAHome.getTotalDraws().toString(), teamAWay.getTotalDraws().toString(), teamBHome.getTotalDraws().toString(), teamBAway.getTotalDraws().toString(),
                teamAHome.getTotalLosses().toString(), teamAWay.getTotalLosses().toString(), teamBHome.getTotalLosses().toString(), teamBAway.getTotalLosses().toString());
        return iaService.getResponse(prompt);
    }
}
