package com.flabbyninja.powertracker.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
abstract public class PowerResponse {
    private String responseCode;

    private List<PowerItem> body;
    private String errorMessage;

    public PowerResponse(String responseCode) {
        this.responseCode = responseCode;
        this.body = new ArrayList<>();
        this.errorMessage = "";
    }
}
