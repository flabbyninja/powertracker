package com.flabbyninja.powertracker;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class PowerService {

    private static final Logger LOGGER = LoggerFactory.getLogger(PowerService.class);

    private final PowerItemRepository powerRepo;

    public PowerService(PropertyService propertyService, PowerItemRepository powerRepo) {
        this.powerRepo = powerRepo;
    }

    @GetMapping("/item/{itemId}")
    public PowerItem getItem(@PathVariable(value="itemId") Long itemId) {
        LOGGER.debug("About to request details of power item with id: " + itemId);
        Optional<PowerItem> returnValue = powerRepo.findById(4L);
        LOGGER.debug("Retrieved item successfully: " + returnValue);
        return null;
    }

    @GetMapping("/brand/{brandName}")
    public List<PowerItem> getBrand(@PathVariable(value="brandName") String brandName) {
        LOGGER.debug("About to lookup by brand: " + brandName);
        List<PowerItem> returnedItems = powerRepo.findByBrand(brandName);
        LOGGER.debug("Service returned " + returnedItems.size() + " items.");
        return returnedItems;
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
