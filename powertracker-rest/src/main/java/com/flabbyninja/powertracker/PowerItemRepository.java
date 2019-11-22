package com.flabbyninja.powertracker;

import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface PowerItemRepository extends CrudRepository<PowerItem, Long> {

    List<PowerItem> findByBrand(String brand);

}
