package com.flabbyninja.powertracker.model;

import lombok.Builder;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class PowerSuccessResponse extends PowerResponse {

    List<PowerItem> body = new ArrayList<>();

    @Builder
    public PowerSuccessResponse(String responseCode, List<PowerItem> body) {
        super(responseCode);
        this.body = body;
    }
}
