package com.neocamp.soccer_matches.testUtils;

import com.neocamp.soccer_matches.entity.StateEntity;

public class StateFactory {

    public static StateEntity createValidState (String name, String code) {
        StateEntity state = new StateEntity();
        state.setName(name);
        state.setCode(code);
        return state;
    }

    public static StateEntity createValidState (String code) {
        return createValidState("State", code);
    }
}
