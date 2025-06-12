package com.NeoCamp.Desafio_Futebol.service;

import com.NeoCamp.Desafio_Futebol.entity.Clube;
import com.NeoCamp.Desafio_Futebol.repository.ClubeRepository;
import com.NeoCamp.Desafio_Futebol.testUtils.ClubeFactory;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

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
        Clube clube =
    }
}
