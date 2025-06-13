package com.NeoCamp.Desafio_Futebol.service;

import com.NeoCamp.Desafio_Futebol.dto.ClubeResponseDto;
import com.NeoCamp.Desafio_Futebol.entity.Clube;
import com.NeoCamp.Desafio_Futebol.repository.ClubeRepository;
import com.NeoCamp.Desafio_Futebol.testUtils.ClubeFactory;
import com.NeoCamp.Desafio_Futebol.testUtils.EstadoFactory;
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

import java.util.List;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
public class ClubeServiceTest {

    @Mock
    private ClubeRepository clubeRepository;

    @Mock
    private EstadoService estadoService;

    @InjectMocks
    private ClubeService clubeService;

    @Test
    public void deveListarTodosClubesComSucesso() {
        Clube clube1 = ClubeFactory.createValidClubeComNomeEId("clube1", 1L);
        Clube clube2 = ClubeFactory.createValidClubeComNomeEId("clube2", 2L);

        List<Clube> listaClubes = List.of(clube1, clube2);

        Pageable pageable = PageRequest.of(0, 10);
        Page<Clube> pageClubes = new PageImpl<>(listaClubes, pageable, listaClubes.size());

        Mockito.when(clubeRepository.findAll(pageable)).thenReturn(pageClubes);

        Page<ClubeResponseDto> resultado = clubeService.findAll(pageable);

        Assertions.assertEquals(2, resultado.getTotalElements());
        Assertions.assertEquals("clube1", resultado.getContent().get(0).getNome());
        Assertions.assertEquals("clube2", resultado.getContent().get(1).getNome());
        Assertions.assertEquals(1L, resultado.getContent().get(0).getId());
        Assertions.assertEquals(2L, resultado.getContent().get(1).getId());
    }

    @Test
    public void deveRetornarClubePorId() {
        Clube clube = ClubeFactory.createValidClubeComNomeEId("clube", 10L);
        Mockito.when(clubeRepository.findById(10L)).thenReturn(Optional.of(clube));

        ClubeResponseDto resultado = clubeService.findById(10L);

        Assertions.assertEquals("clube", resultado.getNome());
        Assertions.assertEquals(10L, resultado.getId());
    }

    @Test
    public void deveLancarExcecaoQuandoIdInvalido() {
        Long idInvalido = -2L;
        Mockito.when(clubeRepository.findById(idInvalido)).thenReturn(Optional.empty());

        ClubeResponseDto resultado = clubeService.findById(idInvalido);

        EntityNotFoundException exception = Assertions.assertThrows(EntityNotFoundException.class,
                () -> clubeService.findById(idInvalido));
        Assertions.assertTrue(exception.getMessage().contains("Clube não encontrado"));
    }
}
