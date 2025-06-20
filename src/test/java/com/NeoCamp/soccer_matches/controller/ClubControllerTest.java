package com.NeoCamp.soccer_matches.controller;

import com.NeoCamp.soccer_matches.service.ClubService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(ClubController.class)
public class ClubControllerTest {

    private Pageable pageable = PageRequest.of(0, 10);

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private ClubService clubService;

    @Test
    public void shouldListAllClubs_WhenAllFiltersAreNull() throws Exception {
    }
}
