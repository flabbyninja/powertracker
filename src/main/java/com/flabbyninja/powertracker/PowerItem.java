package com.flabbyninja.powertracker;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PowerItem {

    private String name;
    private int capacity;

}
