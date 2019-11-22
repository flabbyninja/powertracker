package com.flabbyninja.powertracker;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
public class PowerService {

    private static final Logger LOGGER = LoggerFactory.getLogger(PowerService.class);

    private final PowerItemRepository powerRepo;

    private final PropertyService propertyService;

    public PowerService(PropertyService propertyService, PowerItemRepository powerRepo) {
        this.propertyService = propertyService;
        this.powerRepo = powerRepo;
    }

    @GetMapping("/item/{itemId}")
    public PowerItem getItem(@PathVariable(required=true, value="itemId") Long itemId) {
        LOGGER.debug("About to request details of power item with id: " + itemId);
        Optional<PowerItem> returnValue = powerRepo.findById(4L);
        LOGGER.debug("Retrieved item successfully: " + returnValue);
        return null;
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

    @RequestMapping("/testrepo")
    public void testRepo() {
        System.out.println("\nfindAll()");
        powerRepo.findAll().forEach(x -> System.out.println(x));

        System.out.println("\nfindById(1L)");
        powerRepo.findById(2L).ifPresent(x -> System.out.println(x));

        System.out.println("\nfindByBrand('Worzel')");
        powerRepo.findByBrand("Worzel").forEach(x -> System.out.println(x));
    }
}
