package com.flabbyninja.powertracker;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PowerService {

    private static final Logger LOGGER = LoggerFactory.getLogger(PowerService.class);

    private final PowerItemRepository powerRepo;

    private final PropertyService propertyService;

    public PowerService(PropertyService propertyService, PowerItemRepository powerRepo) {
        this.propertyService = propertyService;
        this.powerRepo = powerRepo;
    }

    @RequestMapping("/power")
    public PowerItem getDetails() {
        LOGGER.debug("About to request details of power item");
        PowerItem returnValue = new PowerItem("Worzel", "SocialPower", "PP9", "NiCad", 4000L, false, "Stockroom");
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

    @RequestMapping("/testrepo")
    public void testRepo() {
        LOGGER.info("StartApplication...");

        PowerItem item1 = new PowerItem("Worzel", "SocialPower", "PP9", "NiCad", 4000L, false, "Stockroom");
        PowerItem item2 = new PowerItem("Worzel", "SocialPower", "PP9", "NiCad", 4000L, false, "Stockroom");
        PowerItem item3 = new PowerItem("Worzel", "SocialPower", "PP9", "NiCad", 4000L, false, "Stockroom");

        System.out.println(item1);
        powerRepo.save(item1);
        powerRepo.save(item2);
        powerRepo.save(item3);

        System.out.println("\nfindAll()");
        powerRepo.findAll().forEach(x -> System.out.println(x));

        System.out.println("\nfindById(1L)");
        powerRepo.findById(4L).ifPresent(x -> System.out.println(x));

        System.out.println("\nfindByBrand('Worzel')");
        powerRepo.findByBrand("Worzel").forEach(x -> System.out.println(x));
    }
}
