package com.neocamp.soccer_matches.testUtils;

import com.neocamp.soccer_matches.dto.state.StateResponseDto;
import com.neocamp.soccer_matches.entity.StateEntity;
import com.neocamp.soccer_matches.enums.StateCode;

public class StateMockUtils {
    public static StateEntity rs() {
        return new StateEntity("Rio Grande do Sul", StateCode.RS);
    }

    public static StateEntity sp() {
        return new StateEntity("São Paulo", StateCode.SP);
    }

    public static StateEntity rj() {
        return new StateEntity("Rio de Janeiro", StateCode.RJ);
    }

    public static StateEntity custom(String name, StateCode stateCode) {
        return new StateEntity(name, stateCode);
    }


    public static StateResponseDto rsDto() {
        return new StateResponseDto(1L,"Rio Grande do Sul", "RS");
    }

    public static StateResponseDto spDto() {
        return new StateResponseDto(2L, "São Paulo", "SP");
    }

    public static StateResponseDto rjDto() {
        return new StateResponseDto(3L,"Rio de Janeiro", "RJ");
    }

    public static StateResponseDto custom(String name, String stateCode) {
        return new StateResponseDto(null, name, stateCode);
    }
}
