package com.flabbyninja.powertracker.integration;

import com.flabbyninja.powertracker.NoItemsAvailableException;
import com.flabbyninja.powertracker.PowerItem;
import com.flabbyninja.powertracker.PowerItemRepository;
import com.flabbyninja.powertracker.PowerService;
import javafx.scene.transform.NonInvertibleTransformException;
import org.assertj.core.util.Lists;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.*;
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

    @BeforeEach
    void setUp() {
        Mockito.when(powerRepo.findByBrand(testItem1.getBrand()))
                .thenReturn(Lists.newArrayList(testItem1));

        Mockito.when(powerRepo.countEntities())
                .thenReturn(3);

        Mockito.when(powerRepo.getFirstAvailableByPowerSize("AAA"))
                .thenReturn(2L);

        Mockito.when(powerRepo.findById(2L)).
                thenReturn(Optional.of(testItem2));
    }

    @Test
    void getExistingValue() {
        List<PowerItem> results = powerService.getBrand("TestBrand");
        assertThat(results).size().isEqualTo(1);
        assertThat(results.get(0).getBrand()).isEqualTo("TestBrand");
    }

    @Test
    void getMissingValue() {
        List<PowerItem> results = powerService.getBrand("MissingBrand");
        assertThat(results).isEmpty();
    }

    @Test
    void checkStock() {
        assertThat(powerService.checkStock()).isEqualTo(3);
    }

    @Test
    void when_available_allocate() {
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
    void when_unavailable_allocate() {
        assertThrows(NoItemsAvailableException.class, () -> {
            PowerItem allocated = powerService.allocateByPowerSize("QQVQ");
        });
    }

    @Test
    void deallocate() {
        fail("Not implemented");
    }

}
