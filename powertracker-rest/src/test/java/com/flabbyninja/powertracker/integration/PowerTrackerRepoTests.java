package com.flabbyninja.powertracker.integration;

import com.flabbyninja.powertracker.model.PowerItem;
import com.flabbyninja.powertracker.jparepositories.PowerItemRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

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
        PowerItem getExisting = powerRepo.findById(savedItemId).get();
        assertThat(sourceItem).isEqualTo(getExisting);
    }

    @Test
    void retrieveSingleMissing() {
        assertThat(powerRepo.findById(9999L)).isEqualTo(Optional.empty());
    }

    @Test
    void findAll() {
        powerRepo.findAll().forEach(x -> System.out.println(x));
    }

    @Test
    void findByBrand() {
        powerRepo.findByBrand("Worzel").forEach(x -> System.out.println(x));
    }
}
