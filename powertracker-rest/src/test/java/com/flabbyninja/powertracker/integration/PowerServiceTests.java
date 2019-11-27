package com.flabbyninja.powertracker.integration;

import com.flabbyninja.powertracker.api.PowerService;
import com.flabbyninja.powertracker.jparepositories.PowerItemRepository;
import com.flabbyninja.powertracker.model.PowerItem;
import com.flabbyninja.powertracker.model.PowerResponse;
import com.flabbyninja.powertracker.model.PowerSuccessResponse;
import org.assertj.core.util.Lists;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.fail;

@SpringBootTest
class PowerServiceTests {

    @Autowired
    private PowerService powerService;

    @MockBean
    private PowerItemRepository powerRepo;

    // Reference test values
    private static final PowerItem testItem1 = new PowerItem("TestBrand", "SocialPower", "AA", "NiCad", 4000L, true, "TestRoom");
    private static final PowerItem testItem2 = new PowerItem("TestBrand", "SocialPower", "AAA", "NiCad", 4000L, true, "TestRoom");
    private static final PowerItem testItem3 = new PowerItem("TestBrand", "SocialPower", "PP9", "NiCad", 4000L, true, "TestRoom");
    private static final PowerItem testItem4 = new PowerItem("TestBrand", "SocialPower", "AAA", "NiCad", 4000L, true, "TestRoom");

    @BeforeEach
    void setUp() {
        testItem1.setId(1L);
        testItem2.setId(2L);
        testItem3.setId(3L);
        testItem4.setId(4L);

        Mockito.when(powerRepo.findByBrand(testItem1.getBrand()))
                .thenReturn(Lists.newArrayList(testItem1));

        Mockito.when(powerRepo.getByPowerSizeAndAvailability("AAA", true))
                .thenReturn(2L);

        Mockito.when(powerRepo.findById(2L)).
                thenReturn(Optional.of(testItem2));

        Mockito.when(powerRepo.findAll()).
                thenReturn(Arrays.asList(testItem1, testItem2, testItem3));
    }

    @Test
    void get_item_when_item_found_then_success_and_item_returned() {
        List<PowerItem> expectedBody = new ArrayList<>();
        expectedBody.add(testItem2);
        PowerResponse expectedResponse = PowerSuccessResponse.builder().statusCode("Success").body(expectedBody).build();
        assertThat(powerService.getItem(2L)).isEqualTo(expectedResponse);
    }

    @Test
    void get_item_when_item_not_found_then_failure_and_empty_list() {
        PowerResponse response = powerService.getItem(762L);
        assertThat(response.getStatusCode()).isEqualTo("Error");
        assertThat(response.getBody()).isEmpty();
    }

    @Test
    void get_brand_when_brand_found_then_success_and_list_of_all_returned() {
        List<PowerItem> expectedBody = new ArrayList<>();
        expectedBody.add(testItem1);
        PowerResponse expectedResponse = PowerSuccessResponse.builder().statusCode("Success").body(expectedBody).build();
        assertThat(powerService.getBrand("TestBrand")).isEqualTo(expectedResponse);
    }

    @Test
    void get_brand_when_brand_not_found_then_success_and_empty_list() {
        PowerResponse response = powerService.getBrand("MissingBrand");
        assertThat(response.getStatusCode()).isEqualTo("Success");
        assertThat(response.getBody()).isEmpty();
    }

    @Test
    void get_all_items_then_all_present() {
        PowerResponse result = powerService.getAllItems();
        assertThat(result.getBody()).isEqualTo(Arrays.asList(testItem1, testItem2, testItem3));
    }

    @Test
    void allocate_when_available_set_unavailable() {
        String desiredPowerSize = "AAA";
        PowerItem reservedItem = new PowerItem(testItem2.getBrand(), testItem2.getModel(), testItem2.getPowerSize(), testItem2.getPowerType(), testItem2.getCapacity(), false, testItem2.getLocation());
        Mockito.when(powerRepo.save(Mockito.any(PowerItem.class)))
                .thenReturn(reservedItem);

        PowerResponse allocated = powerService.allocateByPowerSize(desiredPowerSize);
        assertThat(allocated.getBody()).hasSize(1);
        assertThat(allocated.getBody().get(0).getPowerSize()).isEqualTo(desiredPowerSize);
        assertThat(allocated.getBody().get(0).isAvailable()).isFalse();

    }

    @Test
    void allocate_when_unavailable_return_error() {
        String missingPowerSize = "QQVQ";
        PowerResponse not_allocated = powerService.allocateByPowerSize(missingPowerSize);
        assertThat(not_allocated.getStatusCode()).isEqualTo("Error");
        assertThat(not_allocated.getErrorMessage()).isEqualTo("Could not allocate item of power size: " + missingPowerSize);
    }

    @Test
    void deallocate_when_allocated_set_available() {
        fail("Not implemented yet");
    }

    @Test
    void deallocate_when_not_allocated_then_return_exception() {
        fail("Not implemented yet");
    }
}
