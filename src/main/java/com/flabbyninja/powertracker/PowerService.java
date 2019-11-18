package com.flabbyninja.powertracker;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PowerService {

    @RequestMapping("/power")
    public PowerItem getDetails() {
        return PowerItem.builder().name("test-cell").capacity(1000).build();
    }
}
