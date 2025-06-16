package com.NeoCamp.Desafio_Futebol.testUtils;

import com.NeoCamp.Desafio_Futebol.entity.StateEntity;

public class StateFactory {

    public static StateEntity createValidState() {
        StateEntity state = new StateEntity();
        state.setName("Estado 1");
        return state;
    }

    public static StateEntity createValidStateWithCode (String code) {
        StateEntity state = new StateEntity();
        state.setCode(code);
        return state;
    }
}
