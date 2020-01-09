package com.flabbyninja.powertracker.integration;

import com.flabbyninja.powertracker.api.PowerService;
import com.flabbyninja.powertracker.service.PropertyService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@ContextConfiguration( locations = {
        "classpath:application-test.yml"
})
class PowerTrackerApplicationTests {

    @Autowired
    private PropertyService propService;

    @Autowired
    private PowerService powerService;

    @Test
    void validateClearProperty() {
        assertThat(propService.getProperty()).isEqualTo("test_clear_value");
    }

    @Test
    void validateEncryptedProperty() {
        assertThat(propService.getEncProperty()).isEqualTo("test_encrypted_value");
    }
}
