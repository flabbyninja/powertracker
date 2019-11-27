package com.flabbyninja.powertracker.model;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@Data
@EqualsAndHashCode(callSuper = true)
public class PowerSuccessResponse extends PowerResponse {

    @Builder
    public PowerSuccessResponse(String statusCode, List<PowerItem> body) {
        super(statusCode);
        this.setBody(body);
    }
}
