package com.NeoCamp.Desafio_Futebol.service;

import com.NeoCamp.Desafio_Futebol.dto.ClubRequestDto;
import com.NeoCamp.Desafio_Futebol.dto.ClubResponseDto;
import com.NeoCamp.Desafio_Futebol.entity.ClubEntity;
import com.NeoCamp.Desafio_Futebol.entity.StateEntity;
import com.NeoCamp.Desafio_Futebol.exception.BusinessException;
import com.NeoCamp.Desafio_Futebol.repository.ClubRepository;
import com.NeoCamp.Desafio_Futebol.testUtils.ClubFactory;
import com.NeoCamp.Desafio_Futebol.testUtils.StateFactory;
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

    @InjectMocks
    private ClubService clubService;

    @Test
    public void shouldListAllClubsSuccessfully() {
        ClubEntity club1 = ClubFactory.createValidClubEntity("club1", 1L, "MA");
        ClubEntity club2 = ClubFactory.createValidClubEntity("club2", 2L, "BA");

        List<ClubEntity> clubsList = List.of(club1, club2);

        Pageable pageable = PageRequest.of(0, 10);
        Page<ClubEntity> pageClubes = new PageImpl<>(clubsList, pageable, clubsList.size());

        Mockito.when(clubRepository.findAll(pageable)).thenReturn(pageClubes);

        Page<ClubResponseDto> result = clubService.findAll(pageable);

        Assertions.assertEquals(2, result.getTotalElements());
        Assertions.assertEquals("club1", result.getContent().get(0).getName());
        Assertions.assertEquals("club2", result.getContent().get(1).getName());
        Assertions.assertEquals(1L, result.getContent().get(0).getId());
        Assertions.assertEquals(2L, result.getContent().get(1).getId());
        Assertions.assertEquals("MA", result.getContent().get(0).getHomeState().getCode());
    }

    @Test
    public void shouldReturnClubDtoByIdSuccessfully() {
        ClubEntity club = ClubFactory.createValidClubEntity("club", 10L, "TO");
        Mockito.when(clubRepository.findById(10L)).thenReturn(Optional.of(club));

        ClubResponseDto result = clubService.findById(10L);

        Assertions.assertEquals("club", result.getName());
        Assertions.assertEquals(10L, result.getId());
        Assertions.assertEquals("TO", result.getHomeState().getCode());
    }

    @Test
    public void shouldThrowExceptionInFindByIdWithInvalidId() {
        Long invalidId = -2L;
        Mockito.when(clubRepository.findById(invalidId)).thenReturn(Optional.empty());

        EntityNotFoundException exception = Assertions.assertThrows(EntityNotFoundException.class,
                () -> clubService.findById(invalidId));
        Assertions.assertTrue(exception.getMessage().contains("Club not found: "));
    }

    @Test
    public void shouldReturnClubEntityByIdSuccessfully() {
        ClubEntity club = ClubFactory.createValidClubEntity("club2", 75L, "SP");
        Mockito.when(clubRepository.findById(75L)).thenReturn(Optional.of(club));

        ClubEntity result = clubService.findEntityById(75L);

        Assertions.assertEquals("club2", result.getName());
        Assertions.assertEquals(75L, result.getId());
        Assertions.assertEquals("SP", result.getHomeState().getCode());
    }

    @Test
    public void shouldThrowExceptionInFindEntityByIdWithInvalidId(){
        Long invalidId = -10L;
        Mockito.when(clubRepository.findById(invalidId)).thenReturn(Optional.empty());

        EntityNotFoundException exception = Assertions.assertThrows(EntityNotFoundException.class,
                () -> clubService.findEntityById(invalidId));
        Assertions.assertTrue(exception.getMessage().contains("Club not found: "));
    }

    @Test
    public void shouldSaveClubSuccessfully() {
        ClubRequestDto clubDto = ClubFactory.createValidClubRequestDto("club3",
                "MG", LocalDate.of(2020, 3, 15), true);

        StateEntity state = StateFactory.createValidState("Minas Gerais", "MG");
        Mockito.when(stateService.findByCode("MG")).thenReturn(state);

        ClubEntity savedClub = new ClubEntity();
        savedClub.setId(2L);
        savedClub.setName(clubDto.getName());
        savedClub.setHomeState(state);

        Mockito.when(clubRepository.save(Mockito.any(ClubEntity.class))).thenReturn(savedClub);

        ClubResponseDto result = clubService.save(clubDto);

        Assertions.assertEquals(2L, result.getId());
        Assertions.assertEquals("club3", result.getName());
        Assertions.assertEquals("MG", result.getHomeState().getCode());
    }

    @Test
    public void shouldThrowExceptionInSaveClubWithInvalidStateCode() {
        String invalidStateCode = "XXX";
        ClubRequestDto clubDto = ClubFactory.createValidClubRequestDto("club3",invalidStateCode,
                LocalDate.of(2020, 3, 15), true);

        BusinessException exception = Assertions.assertThrows(BusinessException.class,
                () -> clubService.save(clubDto));

        Assertions.assertEquals("Invalid state code: XXX", exception.getMessage());
        Mockito.verify(clubRepository, Mockito.never()).save(Mockito.any());
    }

    @Test
    public void shouldUpdateClubSuccessfully() {
        Long existingClubId = 1L;

        ClubEntity existingClub = new ClubEntity();
        existingClub.setId(existingClubId);
        existingClub.setName("Club4");
        existingClub.setCreationDate(LocalDate.of(2015, 7, 30));

        ClubRequestDto clubDto = ClubFactory.createValidClubRequestDto("newName", "RO",
                LocalDate.of(2020, 3, 15), true);

        Mockito.when(clubRepository.findById(existingClubId)).thenReturn(Optional.of(existingClub));

        StateEntity state = StateFactory.createValidState("Rondônia", "RO");
        Mockito.when(stateService.findByCode("RO")).thenReturn(state);

        Mockito.when(clubRepository.save(Mockito.any(ClubEntity.class)))
                .thenAnswer(invocation -> invocation.getArgument(0));

        ClubResponseDto result = clubService.update(existingClubId, clubDto);

        Assertions.assertEquals(1L, result.getId());
        Assertions.assertEquals("newName", result.getName());
        Assertions.assertEquals(LocalDate.of(2020, 3, 15), result.getCreationDate());
    }
    @Test
    public void shouldThrowExceptionInUpdateClubWithInvalidId(){
        Long invalidId = -1L;
        ClubRequestDto clubDto = ClubFactory.createValidClubRequestDto("club5", "RS",
                LocalDate.of(2020, 3, 15), true);

        Mockito.when(clubRepository.findById(invalidId)).thenReturn(Optional.empty());

        EntityNotFoundException exception = Assertions.assertThrows(EntityNotFoundException.class,
                () -> clubService.update(invalidId, clubDto));

        Assertions.assertTrue(exception.getMessage().contains("Club not found: "));
    }

    @Test
    public void shouldThrowExceptionInUpdateClubWithInvalidStateCode() {
        Long validId = 5L;
        String invalidStateCode = "YYY";

        ClubRequestDto clubDto = ClubFactory.createValidClubRequestDto("Club6", invalidStateCode,
                LocalDate.of(2020, 3, 15), true);

        ClubEntity existingClub = new ClubEntity();
        existingClub.setId(validId);
        Mockito.when(clubRepository.findById(validId)).thenReturn(Optional.of(existingClub));

        BusinessException exception = Assertions.assertThrows(BusinessException.class,
                () -> clubService.update(validId, clubDto));

        Assertions.assertEquals("Invalid state code: YYY", exception.getMessage());
    }

    @Test
    void shouldMarkClubAsInactiveWhenDeleteIsCalled() {
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
        Assertions.assertFalse(club.isActive());
    }
}
