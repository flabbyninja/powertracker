package com.flabbyninja.powertracker;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PowerItem {

    private String brand;
    private String model;
    private String type;
    private int capacity;

}
