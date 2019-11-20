package com.flabbyninja.powertracker;

import com.ulisesbocchio.jasyptspringboot.annotation.EnableEncryptableProperties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableEncryptableProperties
public class PowerTrackerApplication {

    private static final Logger LOGGER = LoggerFactory.getLogger(PowerTrackerApplication.class);

    @Autowired
    private PowerItemRepository powerRepo;

    public static void main(String[] args) {
        SpringApplication.run(PowerTrackerApplication.class, args);
    }
}
