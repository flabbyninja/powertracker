package com.flabbyninja.powertracker;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface PowerItemRepository extends CrudRepository<PowerItem, Long> {

    List<PowerItem> findByBrand(String brand);

    @Query(value = "SELECT count(*) FROM power_item", nativeQuery = true)
    int countEntities();

}
