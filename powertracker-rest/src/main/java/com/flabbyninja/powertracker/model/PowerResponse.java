package com.flabbyninja.powertracker.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
abstract public class PowerResponse {
    private String statusCode;
    private List<PowerItem> body;
    private String errorMessage;

    public PowerResponse(String statusCode) {
        this.statusCode = statusCode;
        this.body = new ArrayList<>();
        this.errorMessage = "";
    }
}
