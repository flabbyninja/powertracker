package com.flabbyninja.powertracker;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
@Getter
public class PropertyService {

    @Value("${test.value}")
    private String property;

    @Value("${test.enc_value}")
    private String encProperty;
}
