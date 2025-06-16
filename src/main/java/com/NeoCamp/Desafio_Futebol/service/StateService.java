package com.NeoCamp.Desafio_Futebol.service;

import com.NeoCamp.Desafio_Futebol.entity.StateEntity;
import com.NeoCamp.Desafio_Futebol.exception.BusinessException;
import com.NeoCamp.Desafio_Futebol.repository.StateRepository;
import org.springframework.stereotype.Service;

@Service
public class StateService {
    private final StateRepository stateRepository;

    public StateService(StateRepository stateRepository) {
        this.stateRepository = stateRepository;
    }

    public StateEntity findByCode(String code) {
        return stateRepository.findByCode(code.toUpperCase())
                .orElseThrow(() -> new BusinessException("Invalid state code: " + code));
    }
}
