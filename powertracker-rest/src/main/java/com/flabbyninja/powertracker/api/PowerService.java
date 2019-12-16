package com.flabbyninja.powertracker.api;

import com.flabbyninja.powertracker.jparepositories.PowerItemRepository;
import com.flabbyninja.powertracker.model.PowerErrorResponse;
import com.flabbyninja.powertracker.model.PowerItem;
import com.flabbyninja.powertracker.model.PowerResponse;
import com.flabbyninja.powertracker.model.PowerSuccessResponse;
import com.flabbyninja.powertracker.service.PropertyService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
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
    public PowerResponse getItem(@PathVariable(value = "itemId") Long itemId) {
        PowerResponse response;
        LOGGER.debug("About to request details of power item with id: " + itemId);
        Optional<PowerItem> item = powerRepo.findById(itemId);
        LOGGER.debug("Retrieved item: " + item);
        if (item.isPresent()) {
            List<PowerItem> itemList = new ArrayList<PowerItem>();
            itemList.add(item.get());
            LOGGER.info("Data found for item... building PowerSuccessResponse");
            response = PowerSuccessResponse.builder().statusCode("Success").body(itemList).build();
        } else {
            LOGGER.info("Item with id: " + itemId + " does not exist... building PowerErrorResponse");
            response = PowerErrorResponse.builder().statusCode("Error").errorMessage("No item exists with id: " + itemId).build();
        }
        return response;
    }

    @GetMapping("/item")
    public PowerResponse getAllItems() {
        PowerResponse response;
        LOGGER.debug("About to request details of all items");
        Iterable<PowerItem> returnValue = powerRepo.findAll();
        LOGGER.debug("Retrieved items successfully: " + returnValue);
        List<PowerItem> itemList = new ArrayList<>();
        returnValue.forEach(itemList::add);
        LOGGER.debug("Building PowerSuccessResponse");
        response = PowerSuccessResponse.builder().statusCode("Success").body(itemList).build();
        return response;
    }

    @GetMapping("/brand/{brandName}")
    public PowerResponse getBrand(@PathVariable(value = "brandName") String brandName) {
        PowerResponse response;
        LOGGER.debug("About to lookup by brand: " + brandName);
        List<PowerItem> returnedItems = powerRepo.findByBrand(brandName);
        LOGGER.debug("Service returned " + returnedItems.size() + " items.");
        LOGGER.debug("Building PowerSuccessResponse");
        response = PowerSuccessResponse.builder().statusCode("Success").body(returnedItems).build();
        return response;
    }

    @RequestMapping("/allocate/{powerSize}")
    public PowerResponse allocateByPowerSize(@PathVariable(value = "powerSize") String powerSize) {
        PowerResponse response;
        long allocatedId = powerRepo.getByPowerSizeAndAvailability(powerSize, true);
        Optional<PowerItem> targetOptional = powerRepo.findById(allocatedId);
        if ((allocatedId <= 0) || (!targetOptional.isPresent())) {
            response = PowerErrorResponse.builder().statusCode("Error").errorMessage("Could not allocate item of power size: " + powerSize).build();
        } else {
            PowerItem targetItem = targetOptional.get();
            targetItem.setAvailable(false);
            List<PowerItem> itemList = new ArrayList<>();
            itemList.add(powerRepo.save(targetItem));
            response = PowerSuccessResponse.builder().statusCode("Success").body(itemList).build();
        }
        return response;
    }

    @RequestMapping("/deallocate/{powerSize}")
    public PowerResponse deallocateByPowerSize(@PathVariable(value = "powerSize") String powerSize) {
        PowerResponse response;
        long allocatedId = powerRepo.getByPowerSizeAndAvailability(powerSize, false);
        Optional<PowerItem> targetOptional = powerRepo.findById(allocatedId);
        if ((allocatedId <= 0) || (!targetOptional.isPresent())) {
            response = PowerErrorResponse.builder().statusCode("Error").errorMessage("Could not release item of power size: " + powerSize).build();
        } else {
            PowerItem targetItem = targetOptional.get();
            targetItem.setAvailable(true);
            List<PowerItem> itemList = new ArrayList<>();
            itemList.add(powerRepo.save(targetItem));
            response = PowerSuccessResponse.builder().statusCode("Success").body(itemList).build();
        }
        return response;
    }
}
