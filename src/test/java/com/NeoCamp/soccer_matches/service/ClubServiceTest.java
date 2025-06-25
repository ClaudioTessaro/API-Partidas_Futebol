package com.neocamp.soccer_matches.service;

import com.neocamp.soccer_matches.dto.club.ClubRequestDto;
import com.neocamp.soccer_matches.dto.club.ClubResponseDto;
import com.neocamp.soccer_matches.dto.club.ClubStatsResponseDto;
import com.neocamp.soccer_matches.entity.ClubEntity;
import com.neocamp.soccer_matches.entity.StateEntity;
import com.neocamp.soccer_matches.enums.StateCode;
import com.neocamp.soccer_matches.exception.BusinessException;
import com.neocamp.soccer_matches.mapper.ClubMapper;
import com.neocamp.soccer_matches.repository.ClubRepository;
import com.neocamp.soccer_matches.repository.MatchRepository;
import com.neocamp.soccer_matches.testUtils.ClubMockUtils;
import com.neocamp.soccer_matches.testUtils.StateMockUtils;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
public class ClubServiceTest {

    @Mock
    private ClubRepository clubRepository;

    @Mock
    private StateService stateService;

    @Mock
    private ClubMapper clubMapper;

    @Mock
    private MatchRepository matchRepository;

    @InjectMocks
    private ClubService clubService;

    private final Pageable pageable = PageRequest.of(0, 10);

    @Test
    public void shouldListAllClubs_whenAllFiltersAreNull() {
        ClubEntity gremio = ClubMockUtils.gremio();
        ClubEntity flamengo = ClubMockUtils.flamengo();

        ClubResponseDto gremioDto = ClubMockUtils.gremioResponseDto();
        ClubResponseDto flamengoDto = ClubMockUtils.flamengoResponseDto();

        Page<ClubEntity> clubs = new PageImpl<>(List.of(gremio, flamengo), pageable, 2);

        Mockito.when(clubRepository.listClubsByFilters(null, null, null, pageable))
                .thenReturn(clubs);
        Mockito.when(clubMapper.toDto(gremio)).thenReturn(gremioDto);
        Mockito.when(clubMapper.toDto(flamengo)).thenReturn(flamengoDto);

        Page<ClubResponseDto> result = clubService.listClubsByFilters(null, null, null, pageable);

        Assertions.assertEquals(2, result.getTotalElements());
        Assertions.assertEquals("Grêmio", result.getContent().get(0).getName());
        Assertions.assertEquals("Flamengo", result.getContent().get(1).getName());
    }

    @Test
    public void shouldListClubsByName() {
        ClubEntity flamengo = ClubMockUtils.flamengo();
        ClubResponseDto flamengoDto = ClubMockUtils.flamengoResponseDto();

        Page<ClubEntity> clubs = new PageImpl<>(List.of(flamengo), pageable, 1);

        Mockito.when(clubRepository.listClubsByFilters("Flamengo", null,
                null, pageable)).thenReturn(clubs);
        Mockito.when(clubMapper.toDto(flamengo)).thenReturn(flamengoDto);

        Page<ClubResponseDto> result = clubService.listClubsByFilters("Flamengo",
                null, null, pageable);

        Assertions.assertEquals(1, result.getTotalElements());
        Assertions.assertEquals("Flamengo", result.getContent().getFirst().getName());
    }

    @Test
    public void shouldListClubsByHomeState() {
        StateEntity rs = StateMockUtils.rs();
        Mockito.when(stateService.findByCode(StateCode.RS)).thenReturn(rs);

        ClubEntity gremio = ClubMockUtils.gremio();
        ClubResponseDto gremioDto = ClubMockUtils.gremioResponseDto();

        Page<ClubEntity> clubs = new PageImpl<>(List.of(gremio), pageable, 1);

        Mockito.when(clubRepository.listClubsByFilters(null, rs,  null, pageable ))
                .thenReturn(clubs);
        Mockito.when(clubMapper.toDto(gremio)).thenReturn(gremioDto);

        Page<ClubResponseDto> result = clubService.listClubsByFilters(null, "RS", null, pageable);

        Assertions.assertEquals(1, result.getTotalElements());
        Assertions.assertEquals("Grêmio", result.getContent().getFirst().getName());
        Assertions.assertEquals("RS", result.getContent().getFirst().getHomeState().getCode());
    }

    @Test
    public void shouldListClubsByActive() {
        ClubEntity corinthians = ClubMockUtils.corinthians();
        corinthians.setActive(true);

        ClubResponseDto corinthiansDto = ClubMockUtils.corinthiansResponseDto();

        Page<ClubEntity> clubs = new PageImpl<>(List.of(corinthians), pageable, 1);

        Mockito.when(clubRepository.listClubsByFilters(null, null, true, pageable))
                .thenReturn(clubs);
        Mockito.when(clubMapper.toDto(corinthians)).thenReturn(corinthiansDto);

        Page<ClubResponseDto> result = clubService.listClubsByFilters(null, null, true, pageable);

        Assertions.assertEquals(1, result.getTotalElements());
        Assertions.assertEquals("Corinthians", result.getContent().getFirst().getName());
        Assertions.assertEquals("SP", result.getContent().getFirst().getHomeState().getCode());
    }

    @Test
    public void shouldListClubsByHomeStateAndActive() {
        StateEntity rs = StateMockUtils.rs();
        Mockito.when(stateService.findByCode(StateCode.RS)).thenReturn(rs);

        ClubEntity gremio = ClubMockUtils.gremio();
        gremio.setActive(true);

        ClubResponseDto gremioDto = ClubMockUtils.gremioResponseDto();

        Page<ClubEntity> clubs = new PageImpl<>(List.of(gremio), pageable, 1);

        Mockito.when(clubRepository.listClubsByFilters(null, rs, true, pageable))
                .thenReturn(clubs);
        Mockito.when(clubMapper.toDto(gremio)).thenReturn(gremioDto);

        Page<ClubResponseDto> result = clubService.listClubsByFilters(null, "RS", true, pageable);

        Assertions.assertEquals(1, result.getTotalElements());
        Assertions.assertEquals("Grêmio", result.getContent().getFirst().getName());
        Assertions.assertEquals("RS", result.getContent().getFirst().getHomeState().getCode());
    }

    @Test
    public void shouldListClubsByNameAndHomeStateAndActive() {
        StateEntity rj = StateMockUtils.rj();
        Mockito.when(stateService.findByCode(StateCode.RJ)).thenReturn(rj);

        ClubEntity flamengo = ClubMockUtils.flamengo();
        flamengo.setActive(true);

        ClubResponseDto flamengoDto = ClubMockUtils.flamengoResponseDto();

        Page<ClubEntity> clubs = new PageImpl<>(List.of(flamengo), pageable, 1);

        Mockito.when(clubRepository.listClubsByFilters("Flamengo", rj, true, pageable))
                .thenReturn(clubs);
        Mockito.when(clubMapper.toDto(flamengo)).thenReturn(flamengoDto);

        Page<ClubResponseDto> result = clubService.listClubsByFilters("Flamengo", "RJ", true, pageable);

        Assertions.assertEquals(1, result.getTotalElements());
        Assertions.assertEquals("Flamengo", result.getContent().getFirst().getName());
        Assertions.assertEquals("RJ", result.getContent().getFirst().getHomeState().getCode());
        Assertions.assertTrue(result.getContent().getFirst().getActive());
    }

    @Test
    public void shouldReturnEmptyPage_whenNoClubMatchFilters() {
        Mockito.when(clubRepository.listClubsByFilters("XXX", null, null, pageable))
                .thenReturn(Page.empty(pageable));

        Page<ClubResponseDto> result = clubService.listClubsByFilters("XXX", null, null, pageable);

        Assertions.assertTrue(result.isEmpty());
    }

    @Test
    public void shouldReturnClubDtoByIdSuccessfully() {
        ClubEntity gremio = ClubMockUtils.gremio();
        gremio.setId(10L);

        ClubResponseDto gremioDto = ClubMockUtils.gremioResponseDto();
        gremioDto.setId(10L);

        Mockito.when(clubRepository.findById(10L)).thenReturn(Optional.of(gremio));
        Mockito.when(clubMapper.toDto(gremio)).thenReturn(gremioDto);

        ClubResponseDto result = clubService.findById(10L);

        Assertions.assertEquals("Grêmio", result.getName());
        Assertions.assertEquals(10L, result.getId());
        Assertions.assertEquals("RS", result.getHomeState().getCode());
    }

    @Test
    public void shouldThrowExceptionInFindById_whenInvalidId() {
        Long invalidId = -2L;
        Mockito.when(clubRepository.findById(invalidId)).thenReturn(Optional.empty());

        EntityNotFoundException exception = Assertions.assertThrows(EntityNotFoundException.class,
                () -> clubService.findById(invalidId));
        Assertions.assertTrue(exception.getMessage().contains("Club not found: "));
    }

    @Test
    public void shouldReturnClubEntityByIdSuccessfully() {
        ClubEntity flamengo = ClubMockUtils.flamengo();
        flamengo.setId(75L);

        Mockito.when(clubRepository.findById(75L)).thenReturn(Optional.of(flamengo));

        ClubEntity result = clubService.findEntityById(75L);

        Assertions.assertEquals("Flamengo", result.getName());
        Assertions.assertEquals(75L, result.getId());
        Assertions.assertEquals("RJ", result.getHomeState().getCode().name());
    }

    @Test
    public void shouldThrowExceptionInFindEntityById_whenInvalidId(){
        Long invalidId = -10L;
        Mockito.when(clubRepository.findById(invalidId)).thenReturn(Optional.empty());

        EntityNotFoundException exception = Assertions.assertThrows(EntityNotFoundException.class,
                () -> clubService.findEntityById(invalidId));
        Assertions.assertTrue(exception.getMessage().contains("Club not found: "));
    }

    @Test
    public void shouldReturnClubStats_whenValidClubId() {
        Long clubId = 10L;
        ClubStatsResponseDto mockStats = new ClubStatsResponseDto(10L, "Grêmio",
                5L, 9L, 8L, 11L, 15L);

        ClubEntity gremio = ClubMockUtils.gremio();

        Mockito.when(clubRepository.findById(clubId)).thenReturn(Optional.of(gremio));
        Mockito.when(matchRepository.getClubStats(clubId)).thenReturn(mockStats);

        ClubStatsResponseDto result = clubService.getClubStats(clubId);

        Assertions.assertEquals(8, result.getTotalLosses());
        Assertions.assertEquals("Grêmio", result.getClubName());
        Assertions.assertEquals(11, result.getGoalsScored());
    }

    @Test
    public void shouldSaveClubSuccessfully() {
        ClubRequestDto gremioRequest = ClubMockUtils.gremioRequestDto();
        StateEntity rs = StateMockUtils.rs();
        ClubEntity gremioEntity = ClubMockUtils.gremio();

        Mockito.when(stateService.findByCode(StateCode.RS)).thenReturn(rs);
        Mockito.when(clubMapper.toEntity(gremioRequest, rs)).thenReturn(gremioEntity);

        Mockito.when(clubRepository.save(Mockito.any(ClubEntity.class))).thenAnswer
                (invocation -> {
                    ClubEntity entity = invocation.getArgument(0);
                    entity.setId(12L);
                    return entity;
                });

        ClubResponseDto gremioResponse = ClubMockUtils.gremioResponseDto();
        gremioResponse.setId(12L);
        Mockito.when(clubMapper.toDto(Mockito.any(ClubEntity.class))).thenReturn(gremioResponse);

        ClubResponseDto result = clubService.save(gremioRequest);

        Assertions.assertEquals(12L, result.getId());
        Assertions.assertEquals("Grêmio", result.getName());
        Assertions.assertEquals("RS", result.getHomeState().getCode());
    }

    @Test
    public void shouldThrowExceptionInSaveClub_whenInvalidStateCode() {
        String invalidStateCode = "XXX";
        ClubRequestDto clubDto = ClubMockUtils.customRequestDto("club2",invalidStateCode,
                LocalDate.of(2020, 3, 15), true);

        BusinessException exception = Assertions.assertThrows(BusinessException.class,
                () -> clubService.save(clubDto));

        Assertions.assertEquals("Invalid state code: XXX", exception.getMessage());
        Mockito.verify(clubRepository, Mockito.never()).save(Mockito.any());
    }

    @Test
    public void shouldUpdateClubSuccessfully() {
        Long existingClubId = 1L;

        ClubEntity existingClub = ClubMockUtils.customEntity("Club", StateMockUtils.sp(),
                LocalDate.of(2020, 3, 15), false);
        existingClub.setId(existingClubId);

        ClubRequestDto clubDto = ClubMockUtils.customRequestDto("newName", "RJ",
                LocalDate.of(2015, 10, 27), true);

        Mockito.when(clubRepository.findById(existingClubId)).thenReturn(Optional.of(existingClub));

        StateEntity rj = StateMockUtils.rj();
        Mockito.when(stateService.findByCode(StateCode.RJ)).thenReturn(rj);

        Mockito.when(clubRepository.save(Mockito.any(ClubEntity.class)))
                .thenAnswer(invocation -> invocation.getArgument(0));

        ClubResponseDto updatedClub = ClubMockUtils.customResponseDto("newName", StateMockUtils.rjDto(),
                LocalDate.of(2015, 10, 27),true);
        updatedClub.setId(existingClubId);

        Mockito.when(clubMapper.toDto(Mockito.any(ClubEntity.class))).thenReturn(updatedClub);

        ClubResponseDto result = clubService.update(existingClubId, clubDto);

        Assertions.assertEquals(1L, result.getId());
        Assertions.assertEquals("newName", result.getName());
        Assertions.assertEquals("RJ", result.getHomeState().getCode());
        Assertions.assertEquals(LocalDate.of(2015, 10, 27), result.getCreationDate());
    }

    @Test
    public void shouldThrowExceptionInUpdateClub_whenInvalidId(){
        Long invalidId = -1L;
        ClubRequestDto clubDto = ClubMockUtils.customRequestDto("club4", "RS",
                LocalDate.of(2020, 3, 15), true);

        Mockito.when(clubRepository.findById(invalidId)).thenReturn(Optional.empty());

        EntityNotFoundException exception = Assertions.assertThrows(EntityNotFoundException.class,
                () -> clubService.update(invalidId, clubDto));

        Assertions.assertTrue(exception.getMessage().contains("Club not found: "));
    }

    @Test
    public void shouldThrowExceptionInUpdateClub_whenInvalidStateCode() {
        Long validId = 5L;
        String invalidStateCode = "YYY";

        ClubRequestDto clubDto = ClubMockUtils.customRequestDto("Club6", invalidStateCode,
                LocalDate.of(2020, 3, 15), true);

        ClubEntity existingClub = new ClubEntity();
        existingClub.setId(validId);
        Mockito.when(clubRepository.findById(validId)).thenReturn(Optional.of(existingClub));

        BusinessException exception = Assertions.assertThrows(BusinessException.class,
                () -> clubService.update(validId, clubDto));

        Assertions.assertEquals("Invalid state code: YYY", exception.getMessage());
    }

    @Test
    void shouldMarkClubAsInactive_whenDeleteIsCalled() {
        Long existingClubId = 11L;
        ClubEntity existingClub = new ClubEntity();
        existingClub.setId(existingClubId);
        existingClub.setActive(true);

        Mockito.when(clubRepository.findById(existingClubId)).thenReturn(Optional.of(existingClub));

        clubService.delete(existingClubId);

        ArgumentCaptor<ClubEntity> captor = ArgumentCaptor.forClass(ClubEntity.class);
        Mockito.verify(clubRepository).save(captor.capture());
        ClubEntity club = captor.getValue();

        Assertions.assertEquals(existingClubId, club.getId());
        Assertions.assertFalse(club.getActive());
    }
}
