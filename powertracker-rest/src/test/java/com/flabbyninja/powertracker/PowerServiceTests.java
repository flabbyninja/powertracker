package com.flabbyninja.powertracker;

import org.assertj.core.util.Lists;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

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

    @BeforeEach
    public void setUp() {
        PowerItem testItem = new PowerItem("TestBrand", "SocialPower", "PP9", "NiCad", 4000L, false, "TestRoom");
        Mockito.when(powerRepo.findByBrand(testItem.getBrand()))
                .thenReturn(Lists.newArrayList(testItem));
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
        assertThat(powerService.checkStock()).isNotNull();
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
