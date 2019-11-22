package com.flabbyninja.powertracker;

import org.assertj.core.util.Lists;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.fail;

@SpringBootTest
class PowerServiceTests {

    @Autowired
    private PowerService powerService;

    @MockBean
    private PowerItemRepository powerRepo;

    @BeforeEach
    void setUp() {
        PowerItem testItem1 = new PowerItem("TestBrand", "SocialPower", "AA", "NiCad", 4000L, false, "TestRoom");
        PowerItem testItem2 = new PowerItem("TestBrand", "SocialPower", "AAA", "NiCad", 4000L, false, "TestRoom");
        PowerItem testItem3 = new PowerItem("TestBrand", "SocialPower", "PP9", "NiCad", 4000L, false, "TestRoom");

        Mockito.when(powerRepo.findByBrand(testItem1.getBrand()))
                .thenReturn(Lists.newArrayList(testItem1));

        Mockito.when(powerRepo.countEntities())
                .thenReturn(3);
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
    void allocate() {
        fail("Not implemented");
    }

    @Test
    void deallocate() {
        fail("Not implemented");
    }

}
