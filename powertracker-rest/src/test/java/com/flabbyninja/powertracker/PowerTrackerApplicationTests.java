package com.flabbyninja.powertracker;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.fail;

@SpringBootTest
class PowerTrackerApplicationTests {

    @Autowired
    private PropertyService propService;

    @Autowired
    private PowerService powerService;

    @Autowired
    private PowerItemRepository powerRepo;

    @Test
    void contextLoads() {
    }

    @Test
    void validateClearProperty() {
        assertThat(propService.getProperty()).isEqualTo("test_clear_value");
    }

    @Test
    void validateEncryptedProperty() {
        assertThat(propService.getEncProperty()).isEqualTo("test_encrypted_value");
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

    @Test
    void validateRepo() {
        PowerItem item1 = new PowerItem("Worzel", "SocialPower", "PP9", "NiCad", 4000L, false, "Stockroom");
        PowerItem item2 = new PowerItem("Worzel", "SocialPower", "PP9", "NiCad", 4000L, false, "Stockroom");
        PowerItem item3 = new PowerItem("Worzel", "SocialPower", "PP9", "NiCad", 4000L, false, "Stockroom");

        powerRepo.save(item1);
        powerRepo.save(item2);
        powerRepo.save(item3);

        System.out.println("\nfindAll()");
        powerRepo.findAll().forEach(x -> System.out.println(x));

        System.out.println("\nfindById(1L)");
        powerRepo.findById(4L).ifPresent(x -> System.out.println(x));

        System.out.println("\nfindByBrand('Worzel')");
        powerRepo.findByBrand("Worzel").forEach(x -> System.out.println(x));

        fail("Purposely fail until test completed");
    }
}
