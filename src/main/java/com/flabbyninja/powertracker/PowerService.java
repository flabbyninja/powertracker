package com.flabbyninja.powertracker;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PowerService {

    @Autowired
    private PropertyService propertyService;

    @RequestMapping("/power")
    public PowerItem getDetails() {
        System.out.println("Standard Property: " + propertyService.getProperty());
        System.out.println("Enc Property: " + propertyService.getEncProperty());
        return PowerItem.builder().brand("duracell").model("hybrio").type("AA").capacity(2200).build();
    }
}
