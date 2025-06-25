package com.neocamp.soccer_matches.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.neocamp.soccer_matches.dto.club.ClubRequestDto;
import com.neocamp.soccer_matches.dto.club.ClubResponseDto;
import com.neocamp.soccer_matches.dto.club.ClubStatsResponseDto;
import com.neocamp.soccer_matches.dto.club.ClubVersusClubStatsDto;
import com.neocamp.soccer_matches.dto.state.StateResponseDto;
import com.neocamp.soccer_matches.service.ClubService;
import com.neocamp.soccer_matches.testUtils.ClubMockUtils;
import com.neocamp.soccer_matches.testUtils.StateMockUtils;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ClubController.class)
public class ClubControllerTest {

    private final Pageable pageable = PageRequest.of(0, 10);

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private ClubService clubService;
    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void shouldReturnAllClubs_whenNoFiltersProvided() throws Exception {
        ClubResponseDto flamengoDto = ClubMockUtils.flamengoResponseDto();
        ClubResponseDto gremioDto = ClubMockUtils.gremioResponseDto();

        Page<ClubResponseDto> clubs = new PageImpl<>(List.of(flamengoDto, gremioDto), pageable, 2);

        Mockito.when(clubService.listClubsByFilters(null, null, null, pageable)).thenReturn(clubs);

        mockMvc.perform(get("/clubs")
                        .param("page", "0")
                        .param("size", "10"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content[0].name").value("Flamengo"))
                .andExpect(jsonPath("$.content[1].name").value("Grêmio"));
    }

    @Test
    public void shouldReturn200AndClubList_whenFilteringByName() throws Exception {
        ClubResponseDto flamengoDto = ClubMockUtils.flamengoResponseDto();

        Page<ClubResponseDto> clubs = new PageImpl<>(List.of(flamengoDto), pageable, 1);

        Mockito.when(clubService.listClubsByFilters("fla", null, null, pageable)).thenReturn(clubs);

        mockMvc.perform(get("/clubs")
                .param("name", "fla")
                .param("page", "0")
                .param("size", "10"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content.length()").value(1))
                .andExpect(jsonPath("$.content[0].name").value("Flamengo"));
    }

    @Test
    public void shouldReturn200AndClubList_whenFilteringByHomeState() throws Exception {
        ClubResponseDto corinthiansDto = ClubMockUtils.corinthiansResponseDto();

        Page<ClubResponseDto> clubs = new PageImpl<>(List.of(corinthiansDto), pageable, 1);

        Mockito.when(clubService.listClubsByFilters(null, "SP", null, pageable))
                .thenReturn(clubs);

        mockMvc.perform(get("/clubs")
                .param("stateCode", "SP")
                .param("page", "0")
                .param("size", "10"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content.length()").value(1))
                .andExpect(jsonPath("$.content[0].name").value("Corinthians"));
    }

    @Test
    public void shouldReturn200AndClubList_whenFilteringByActive() throws Exception {
        ClubResponseDto gremioDto = ClubMockUtils.gremioResponseDto();
        gremioDto.setActive(true);

        Page<ClubResponseDto> clubs = new PageImpl<>(List.of(gremioDto), pageable, 1);

        Mockito.when(clubService.listClubsByFilters(null, null, true, pageable))
                .thenReturn(clubs);

        mockMvc.perform(get("/clubs")
                .param("active", "true")
                .param("page", "0")
                .param("size", "10"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content.length()").value(1))
                .andExpect(jsonPath("$.content[0].name").value("Grêmio"));
    }

    @Test
    public void shouldReturn200AndClubList_whenFilteringByNameAndHomeStateAndActive() throws Exception {
        ClubResponseDto corinthiansDto = ClubMockUtils.corinthiansResponseDto();

        Page<ClubResponseDto> clubs = new PageImpl<>(List.of(corinthiansDto), pageable, 1);

        Mockito.when(clubService.listClubsByFilters("cor", "SP", true, pageable))
                .thenReturn(clubs);

        mockMvc.perform(get("/clubs")
                .param("name", "cor")
                .param("stateCode", "SP")
                .param("active", "true")
                .param("page", "0")
                .param("size", "10"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content.length()").value(1))
                .andExpect(jsonPath("$.content[0].name").value("Corinthians"));
    }

    @Test
    public void shouldReturn200AndEmptyList_whenInvalidStateCode() throws Exception {
        Page<ClubResponseDto> emptyClubs = new PageImpl<>(List.of(), pageable, 0);

        Mockito.when(clubService.listClubsByFilters(null, "XX", null, pageable))
                .thenReturn(emptyClubs);

        mockMvc.perform(get("/clubs")
                .param("stateCode", "XX")
                .param("page", "0")
                .param("size", "10"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content.length()").value(0));
    }

    @Test
    public void shouldReturn200AndClubDetails_whenFindById() throws Exception {
        ClubResponseDto gremioDto = ClubMockUtils.gremioResponseDto();
        gremioDto.setId(1L);

        Mockito.when(clubService.findById(1L)).thenReturn(gremioDto);

        mockMvc.perform(get("/clubs/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.name").value("Grêmio"));
    }

    @Test
    public void shouldReturn404_whenFindByIdWithInvalidId() throws Exception {
        Mockito.when(clubService.findById(-1L)).thenThrow(new EntityNotFoundException("Club not found"));

        mockMvc.perform(get("/clubs/-1"))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.message").value("Club not found"))
                .andExpect(jsonPath("$.error").value("Not Found"));
    }

    @Test
    public void shouldReturn200AndClubStats_whenGetClubStatsWithValidId() throws Exception {
        Long clubId = 8L;

        ClubStatsResponseDto mockStats = new ClubStatsResponseDto(clubId, "Club",
                8L, 4L, 2L, 14L, 9L);

        Mockito.when(clubService.getClubStats(8L)).thenReturn(mockStats);

        mockMvc.perform(get("/clubs/8/stats"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.clubName").value("Club"))
                .andExpect(jsonPath("$.clubId").value(8L));
    }

    @Test
    public void shouldReturn200AndClubVersusClubStats_whenGetClubVersusClubStatsWithValidIds() throws Exception {
        Long clubId = 12L;
        Long opponentId = 23L;

        ClubVersusClubStatsDto mockVersusStats = new ClubVersusClubStatsDto(clubId, "Coritiba", opponentId,
                "Santos", 5L, 3L, 6L, 15L, 9L );

        Mockito.when(clubService.getClubVersusClubStats(clubId, opponentId)).thenReturn(mockVersusStats);

        mockMvc.perform(get("/clubs/12/versus/23"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.clubName").value("Coritiba"))
                .andExpect(jsonPath("$.opponentName").value("Santos"))
                .andExpect(jsonPath("$.totalWins").value(5L))
                .andExpect(jsonPath("$.totalLosses").value(6L));
    }

    @Test
    public void shouldReturn201AndClubDetails_whenCreateValidClub() throws Exception {
        ClubRequestDto gremioRequestDto = ClubMockUtils.gremioRequestDto();
        ClubResponseDto gremioResponseDto = ClubMockUtils.gremioResponseDto();

        Mockito.when(clubService.save(gremioRequestDto)).thenReturn(gremioResponseDto);

        mockMvc.perform(post("/clubs")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(gremioRequestDto)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.name").value("Grêmio"));
    }

    @Test
    public void shouldReturn400_whenCreateWithMissingParameter() throws Exception {
        ClubRequestDto invalidDto = ClubMockUtils.customRequestDto(null, "TO",
                LocalDate.now(), true);

        mockMvc.perform(post("/clubs")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(invalidDto)))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void shouldReturn200AndClubDetails_whenUpdateValidClub() throws Exception {
        ClubRequestDto requestDto = ClubMockUtils.customRequestDto("oldName", "RO",
                LocalDate.now(), true);

        StateResponseDto rjDto = StateMockUtils.rjDto();
        ClubResponseDto responseDto = ClubMockUtils.customResponseDto("newName", rjDto,
                LocalDate.of(2020, 6, 18), false);
        responseDto.setId(5L);

        Mockito.when(clubService.update(5L, requestDto)).thenReturn(responseDto);

        mockMvc.perform(put("/clubs/5")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(requestDto)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(5))
                .andExpect(jsonPath("$.name").value("newName"))
                .andExpect(jsonPath("$.active").value(false));
    }

    @Test
    public void shouldReturn204_whenDeleteClub() throws Exception {
        Long id = 19L;

        mockMvc.perform(delete("/clubs/{id}", id))
                .andExpect(status().isNoContent());

        Mockito.verify(clubService, Mockito.times(1)).delete(id);
    }

    @Test
    public void shouldReturn404_whenDeleteWithInvalidId() throws Exception {
        Long invalidId = -1L;

        Mockito.doThrow(new EntityNotFoundException("Club not found: " + invalidId))
                .when(clubService).delete(invalidId);

        mockMvc.perform(delete("/clubs/{id}", invalidId))
                .andExpect(status().isNotFound());
    }
}
