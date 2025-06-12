package com.NeoCamp.Desafio_Futebol.service;

import com.NeoCamp.Desafio_Futebol.entity.Estado;
import com.NeoCamp.Desafio_Futebol.exception.NegocioException;
import com.NeoCamp.Desafio_Futebol.repository.EstadoRepository;
import org.springframework.stereotype.Service;

@Service
public class EstadoService {
    private final EstadoRepository estadoRepository;

    public EstadoService(EstadoRepository estadoRepository) {
        this.estadoRepository = estadoRepository;
    }

    public Estado buscarPorSigla(String sigla) {
        return estadoRepository.findBySigla(sigla.toUpperCase())
                .orElseThrow(() -> new NegocioException("Sigla do estado inválida: " + sigla));
    }
}
