package com.neocamp.soccer_matches.service;

import com.neocamp.soccer_matches.enums.StateCode;
import com.neocamp.soccer_matches.repository.StateRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class StateServiceTest {

    @Mock
    private StateRepository stateRepository;

    @InjectMocks
    private StateService stateService;

    @Test
    public void shouldFindStateByCodeSuccessfully() {
        //StateCode stateCode = new StateCode();
        //Mockito.when(stateRepository.findByCode());
    }
}
