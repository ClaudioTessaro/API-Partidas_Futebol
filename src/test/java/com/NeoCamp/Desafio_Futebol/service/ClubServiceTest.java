package com.NeoCamp.Desafio_Futebol.service;

import com.NeoCamp.Desafio_Futebol.dto.ClubRequestDto;
import com.NeoCamp.Desafio_Futebol.dto.ClubResponseDto;
import com.NeoCamp.Desafio_Futebol.entity.ClubEntity;
import com.NeoCamp.Desafio_Futebol.repository.ClubRepository;
import com.NeoCamp.Desafio_Futebol.testUtils.ClubFactory;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
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
        ClubEntity club1 = ClubFactory.createValidClubWithNameAndId("club1", 1L);
        ClubEntity club2 = ClubFactory.createValidClubWithNameAndId("club2", 2L);

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
    }

    @Test
    public void shouldReturnClubDtoByIdSuccessfully() {
        ClubEntity club = ClubFactory.createValidClubWithNameAndId("club", 10L);
        Mockito.when(clubRepository.findById(10L)).thenReturn(Optional.of(club));

        ClubResponseDto result = clubService.findById(10L);

        Assertions.assertEquals("club", result.getName());
        Assertions.assertEquals(10L, result.getId());
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
        ClubEntity club = ClubFactory.createValidClubWithNameAndId("club2", 75L);
        Mockito.when(clubRepository.findById(75L)).thenReturn(Optional.of(club));

        ClubEntity result = clubService.findEntityById(75L);

        Assertions.assertEquals("club2", result.getName());
        Assertions.assertEquals(75L, result.getId());
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
                "SP", LocalDate.now(), true);
        ClubEntity club = ClubFactory
        Mockito.when(clubRepository.save())
    }
}
