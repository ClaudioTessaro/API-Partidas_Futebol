package com.neocamp.soccer_matches.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.neocamp.soccer_matches.dto.stadium.StadiumRequestDto;
import com.neocamp.soccer_matches.dto.stadium.StadiumResponseDto;
import com.neocamp.soccer_matches.service.StadiumService;
import com.neocamp.soccer_matches.testUtils.StadiumMockUtils;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.List;

@WebMvcTest(StadiumController.class)
public class StadiumControllerTest {

    private final Pageable pageable = PageRequest.of(0, 10);

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockitoBean
    private StadiumService stadiumService;

    @Test
    public void shouldReturnAllStadiums() throws Exception {
        StadiumResponseDto maracana = StadiumMockUtils.maracanaResponseDto();
        StadiumResponseDto morumbi = StadiumMockUtils.morumbiResponseDto();

        Page<StadiumResponseDto> stadiums = new PageImpl<>(List.of(maracana, morumbi), pageable, 2);

        Mockito.when(stadiumService.findAll(pageable)).thenReturn(stadiums);

        mockMvc.perform(get("/stadiums")
                .param("page", "0")
                .param("size", "10"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content.length()").value(2))
                .andExpect(jsonPath("$.content[0].name").value("Maracanã"))
                .andExpect(jsonPath("$.content[1].name").value("Morumbi"));
    }

    @Test
    public void shouldReturn200AndStadiumDetails_whenFindById() throws Exception {
        StadiumResponseDto maracana = StadiumMockUtils.maracanaResponseDto();
        maracana.setId(1L);

        Mockito.when(stadiumService.findById(1L)).thenReturn(maracana);

        mockMvc.perform(get("/stadiums/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.name").value("Maracanã"));
    }

    @Test
    public void shouldReturn404_whenFindByIdWithInvalidId() throws Exception {
        Long invalidId = -10L;

        Mockito.when(stadiumService.findById(invalidId)).thenThrow(new EntityNotFoundException("Stadium not found"));

        mockMvc.perform(get("/stadiums/-10"))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.message").value("Stadium not found"))
                .andExpect(jsonPath("$.status").value("404"));
    }

    @Test
    public void shouldReturn201AndStadiumDetails_whenCreateValidStadium() throws Exception {
        StadiumRequestDto morumbiRequestDto = StadiumMockUtils.morumbiRequestDto();
        StadiumResponseDto morumbiResponseDto = StadiumMockUtils.morumbiResponseDto();

        Mockito.when(stadiumService.save(morumbiRequestDto)).thenReturn(morumbiResponseDto);

        mockMvc.perform(post("/stadiums")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(morumbiRequestDto)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.name").value("Morumbi"));
    }

    @Test
    public void shouldReturn400_whenCreateWithMissingParameter() throws Exception {
        StadiumRequestDto invalidDto = StadiumMockUtils.customRequestDto(null);

        mockMvc.perform(post("/stadiums")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(invalidDto)))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void shouldReturn200AndStadiumDetails_whenUpdateValidStadium() throws Exception {
        Long stadiumId = 1L;

        StadiumRequestDto updateRequestDto = StadiumMockUtils.customRequestDto("Beira-Rio");
        StadiumResponseDto updatedResponseDto = StadiumMockUtils.customResponseDto("Beira-Rio");
        updatedResponseDto.setId(stadiumId);

        Mockito.when(stadiumService.update(stadiumId, updateRequestDto)).thenReturn(updatedResponseDto);

        mockMvc.perform(put("/stadiums/{id}", stadiumId)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(updateRequestDto)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Beira-Rio"))
                .andExpect(jsonPath("$.id").value(1));
    }
}
