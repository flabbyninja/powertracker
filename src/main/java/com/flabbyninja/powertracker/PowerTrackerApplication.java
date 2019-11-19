package com.flabbyninja.powertracker;

import com.ulisesbocchio.jasyptspringboot.annotation.EnableEncryptableProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableEncryptableProperties
public class PowerTrackerApplication {

    public static void main(String[] args) {
        SpringApplication.run(PowerTrackerApplication.class, args);
    }

}
