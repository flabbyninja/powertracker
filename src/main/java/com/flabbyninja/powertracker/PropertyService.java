package com.flabbyninja.powertracker;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
@Getter
public class PropertyService {

    @Value("${credentials.clear}")
    private String property;

    @Value("${credentials.enc}")
    private String encProperty;
}
