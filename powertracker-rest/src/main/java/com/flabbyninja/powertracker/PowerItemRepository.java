package com.flabbyninja.powertracker;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PowerItemRepository extends CrudRepository<PowerItem, Long> {

    List<PowerItem> findByBrand(String brand);

    @Query(value = "SELECT count(*) FROM power_item", nativeQuery = true)
    int countEntities();

    @Query(value = "select MIN(id) from power_item where power_size=:power_size and available=true", nativeQuery = true)
    long getFirstAvailableByPowerSize(@Param("power_size") String powerSize);

}
