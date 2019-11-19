package com.flabbyninja.powertracker;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PowerService {

    private static final Logger LOGGER = LoggerFactory.getLogger(PowerService.class);

    private final PropertyService propertyService;

    public PowerService(PropertyService propertyService) {
        this.propertyService = propertyService;
    }

    @RequestMapping("/power")
    public PowerItem getDetails() {
        LOGGER.debug("About to request details of power item");
        PowerItem returnValue = PowerItem.builder().brand("duracell").model("hybrio").type("AA").capacity(2200).build();
        LOGGER.debug("Got power item: " + returnValue);
        return returnValue;
    }

    @RequestMapping("/stock")
    public String checkStock() {
        return null;
    }

    @RequestMapping("/allocate")
    public void allocate() {

    }

    @RequestMapping("/deallocate")
    public void deallocate() {

    }
}
