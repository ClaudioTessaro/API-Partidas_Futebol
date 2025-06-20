package com.NeoCamp.soccer_matches.service;

import com.NeoCamp.soccer_matches.entity.StateEntity;
import com.NeoCamp.soccer_matches.exception.BusinessException;
import com.NeoCamp.soccer_matches.repository.StateRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class StateService {
    private final StateRepository stateRepository;

    public StateEntity findByCode(String code) {
        return stateRepository.findByCode(code.toUpperCase())
                .orElseThrow(() -> new BusinessException("Invalid state code: " + code));
    }
}
