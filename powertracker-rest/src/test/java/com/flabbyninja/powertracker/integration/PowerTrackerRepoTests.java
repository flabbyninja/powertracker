package com.flabbyninja.powertracker.integration;

import com.flabbyninja.powertracker.jparepositories.PowerItemRepository;
import com.flabbyninja.powertracker.model.PowerItem;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class PowerTrackerRepoTests {

    @Autowired
    private PowerItemRepository powerRepo;

    @Test
    void repoAddSingle() {
        PowerItem inputItem = new PowerItem("Worzel", "SocialPower", "PP9", "NiCad", 4000L, false, "Stockroom");
        PowerItem savedItem = powerRepo.save(inputItem);
        assertThat(savedItem).isEqualTo(inputItem);
    }

    @Test
    void retrieveSingleExisting() {
        PowerItem sourceItem = new PowerItem("Worzel", "SocialPower", "PP9", "NiCad", 4000L, false, "Stockroom");
        Long savedItemId = powerRepo.save(sourceItem).getId();
        PowerItem getExisting = powerRepo.findById(savedItemId).orElse(null);
        assertThat(sourceItem).isEqualTo(getExisting);
    }

    @Test
    void getByPowerSizeAndAvailability() {

        assertThat(powerRepo.getByPowerSizeAndAvailability("AAA", true)).isEqualTo(5L);
        assertThat(powerRepo.getByPowerSizeAndAvailability("AAA", false)).isEqualTo(4L);
    }
}
