package com.neocamp.soccer_matches.dto.match;

import com.neocamp.soccer_matches.dto.club.ClubVersusClubStatsDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class HeadToHeadResponseDto {
    private ClubVersusClubStatsDto stats;
    private List<MatchResponseDto> matches;
}
