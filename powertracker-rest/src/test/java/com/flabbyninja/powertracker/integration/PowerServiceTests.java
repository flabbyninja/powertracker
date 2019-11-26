package com.flabbyninja.powertracker.integration;

import com.flabbyninja.powertracker.exception.NoItemsAvailableException;
import com.flabbyninja.powertracker.model.PowerItem;
import com.flabbyninja.powertracker.jparepositories.PowerItemRepository;
import com.flabbyninja.powertracker.api.PowerService;
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
import static org.junit.jupiter.api.Assertions.assertThrows;

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

        Mockito.when(powerRepo.countEntities())
                .thenReturn(3);

        Mockito.when(powerRepo.getByPowerSizeAndAvailability("AAA", true))
                .thenReturn(2L);

        Mockito.when(powerRepo.findById(2L)).
                thenReturn(Optional.of(testItem2));

        Mockito.when(powerRepo.findAll()).
                thenReturn(Arrays.asList(testItem1, testItem2, testItem3));
    }

    @Test
    void when_item_found_then_item_returned() {
        assertThat(powerService.getItem(2L)).isEqualTo(Optional.of(testItem2));
    }

    @Test
    void when_item_not_found_then_empty_optional() {
        assertThat(powerService.getItem(762L).isPresent()).isFalse();
    }

    @Test
    void when_brand_found_then_list_of_all_returned() {
        List<PowerItem> results = powerService.getBrand("TestBrand");
        assertThat(results).size().isEqualTo(1);
        assertThat(results.get(0).getBrand()).isEqualTo("TestBrand");
    }

    @Test
    void when_brand_not_found_then_empty_list() {
        List<PowerItem> results = powerService.getBrand("MissingBrand");
        assertThat(results).isEmpty();
    }

    @Test
    void when_all_items_returned_then_all_present() {
        Iterable<PowerItem> resultsIt = powerService.getAllItems();
        List<PowerItem> resultList = new ArrayList<>();
        resultsIt.forEach(
                resultList::add
        );
        assertThat(resultList).isEqualTo(Arrays.asList(testItem1, testItem2, testItem3));
    }

    @Test
    void when_stock_checked_then_correct_total() {
        assertThat(powerService.checkStock()).isEqualTo(3);
    }

    @Test
    void when_available_allocate_and_set_unavailable() {
        String desiredPowerSize = "AAA";

        PowerItem reservedItem = new PowerItem(testItem2.getBrand(), testItem2.getModel(), testItem2.getPowerSize(), testItem2.getPowerType(), testItem2.getCapacity(), false, testItem2.getLocation());
        Mockito.when(powerRepo.save(Mockito.any(PowerItem.class)))
                .thenReturn(reservedItem);

        try {
            PowerItem allocated = powerService.allocateByPowerSize(desiredPowerSize);
            assertThat(allocated.getPowerSize()).isEqualTo(desiredPowerSize);
            assertThat(allocated.isAvailable()).isFalse();
        } catch (NoItemsAvailableException nie) {
            fail("No Items available - test has failed");
        }
    }

    @Test
    void when_unavailable_allocate_then_return_exception() {
        assertThrows(NoItemsAvailableException.class, () -> {
            PowerItem allocated = powerService.allocateByPowerSize("QQVQ");
        });
    }

    @Test
    void when_allocated_deallocate_and_set_available() {
        fail("Not implemented");
    }

    @Test
    void when_none_allocated_then_return_exception() {
        fail("Not implemented");
    }
}
