package com.neocamp.soccer_matches.controller;

import com.neocamp.soccer_matches.dto.ClubResponseDto;
import com.neocamp.soccer_matches.service.ClubService;
import com.neocamp.soccer_matches.testUtils.ClubFactory;
import org.junit.jupiter.api.Test;
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

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ClubController.class)
public class ClubControllerTest {

    private Pageable pageable = PageRequest.of(0, 10);

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private ClubService clubService;

    @Test
    public void shouldListAllClubs_WhenAllFiltersAreNull() throws Exception {
        ClubResponseDto flamengo = ClubFactory.createValidClubResponseDto("Flamengo", "RJ",
                LocalDate.now(), true);
        ClubResponseDto gremio = ClubFactory.createValidClubResponseDto("Corinthians", "SP",
                LocalDate.now(), true);

        Page<ClubResponseDto> clubs = new PageImpl<>(List.of(flamengo, gremio), pageable, 2);

        Mockito.when(clubService.listClubsByFilters(null, null, null, pageable)).thenReturn(clubs);

        mockMvc.perform(get("/clubs")
                        .param("page", "0")
                        .param("size", "10"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content[0].name").value("Flamengo"))
                .andExpect(jsonPath("$.content[1].name").value("Corinthians"));
    }

    @Test
    public void shouldListClubsByName() throws Exception {
        ClubResponseDto internacional = ClubFactory.createValidClubResponseDto("Internacional", "RS",
                LocalDate.now(), true);
        ClubResponseDto coritiba = ClubFactory.createValidClubResponseDto("Coritiba", "PR",
                LocalDate.now(), true);

        Page<ClubResponseDto> clubs = new PageImpl<>(List.of(internacional, coritiba), pageable, 2);

        Mockito.when(clubService.listClubsByFilters("l", null, null, pageable)).thenReturn(clubs);

        mockMvc.perform(get("/clubs")
                .param("name", "l")
                .param("page", "0")
                .param("size", "10"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content.length()").value(1))
                .andExpect(jsonPath("$.content[0].name").value("Internacional"));
    }
}
