package com.flabbyninja.powertracker.model;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class PowerErrorResponse extends PowerResponse {
    private String errorMessage;

    @Builder
    public PowerErrorResponse(String statusCode, String errorMessage) {
        super(statusCode);
        this.errorMessage = errorMessage;
    }
}
