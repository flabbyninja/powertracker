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
    public PowerItem getItem(@PathVariable(value = "itemId") Long itemId) {
        LOGGER.debug("About to request details of power item with id: " + itemId);
        Optional<PowerItem> returnValue = powerRepo.findById(itemId);
        LOGGER.debug("Retrieved item successfully: " + returnValue);
        return null;
    }

    @GetMapping("/item")
    public Iterable<PowerItem> getAllItems() {
        LOGGER.debug("About to request details of all items");
        Iterable<PowerItem> returnValue = powerRepo.findAll();
        LOGGER.debug("Retrieved items successfully: " + returnValue);
        return returnValue;
    }

    @GetMapping("/brand/{brandName}")
    public List<PowerItem> getBrand(@PathVariable(value = "brandName") String brandName) {
        LOGGER.debug("About to lookup by brand: " + brandName);
        List<PowerItem> returnedItems = powerRepo.findByBrand(brandName);
        LOGGER.debug("Service returned " + returnedItems.size() + " items.");
        return returnedItems;
    }

    @RequestMapping("/stock")
    public int checkStock() {
        return powerRepo.countEntities();
    }

    @RequestMapping("/allocate/{powerSize}")
    public PowerItem allocateByPowerSize(@PathVariable(value = "powerSize") String powerSize)
            throws NoItemsAvailableException {
        long allocatedId = powerRepo.getFirstAvailableByPowerSize(powerSize);
        if (allocatedId <= 0) {
            throw new NoItemsAvailableException("No available power items of size " + powerSize);
        }
        PowerItem targetItem = powerRepo.findById(allocatedId).get();
        targetItem.setAvailable(false);
        return powerRepo.save(targetItem);
    }

    @RequestMapping("/deallocate")
    public void deallocate() {

    }
}
