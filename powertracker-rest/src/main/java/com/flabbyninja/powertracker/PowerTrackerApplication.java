package com.flabbyninja.powertracker;

import com.flabbyninja.powertracker.jparepositories.PowerItemRepository;
import com.ulisesbocchio.jasyptspringboot.annotation.EnableEncryptableProperties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableEncryptableProperties
public class PowerTrackerApplication {

    private static final Logger LOGGER = LoggerFactory.getLogger(PowerTrackerApplication.class);

    private final PowerItemRepository powerRepo;

    public PowerTrackerApplication(PowerItemRepository powerRepo) {
        this.powerRepo = powerRepo;
    }

    public static void main(String[] args) {
        SpringApplication.run(PowerTrackerApplication.class, args);
    }
}
