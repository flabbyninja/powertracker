package com.flabbyninja.powertracker;

import lombok.*;

import javax.persistence.*;
import java.util.Objects;

@Getter
@ToString
@Entity
public class PowerItem {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;
    private String brand;
    private String model;
    private String powerSize;
    private String powerType;
    private Long capacity;
    private boolean available;
    private String location;

    protected PowerItem() {};

    public PowerItem(String brand, String model, String powerSize, String powerType, Long capacity, boolean available, String location) {
        this.brand = brand;
        this.model = model;
        this.powerSize = powerSize;
        this.powerType = powerType;
        this.capacity = capacity;
        this.available = available;
        this.location = location;
    }

    // Implement equals() and hashCode() manually as Lombok generated versions are incompatible with JPA and id validation

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PowerItem)) return false;
        PowerItem item = (PowerItem) o;
        return Objects.equals(getId(), item.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }
}
