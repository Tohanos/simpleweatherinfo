package com.example.simpleweatherinfo.dao;

import com.example.simpleweatherinfo.domain.City;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CityRepository extends JpaRepository<City, Long> {
    City findFirstByName(String name);
}
