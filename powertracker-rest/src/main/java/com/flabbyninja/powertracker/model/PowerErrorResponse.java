package com.flabbyninja.powertracker.model;

import lombok.Builder;
import lombok.Data;

@Data
public class PowerErrorResponse extends PowerResponse {
    private String errorMessage;

    @Builder
    public PowerErrorResponse(String responseCode, String errorMessage) {
        super(responseCode);
        this.errorMessage = errorMessage;
    }
}
