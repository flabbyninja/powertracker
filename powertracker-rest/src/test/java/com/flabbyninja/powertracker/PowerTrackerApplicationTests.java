package com.flabbyninja.powertracker;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class PowerTrackerApplicationTests {

    @Autowired
    private PropertyService propService;

    @Autowired
    private PowerService powerService;

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
}
