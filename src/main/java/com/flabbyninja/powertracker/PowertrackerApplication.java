package com.flabbyninja.powertracker;

import com.ulisesbocchio.jasyptspringboot.annotation.EnableEncryptableProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableEncryptableProperties
public class PowertrackerApplication {

    public static void main(String[] args) {
        SpringApplication.run(PowertrackerApplication.class, args);
    }

}
