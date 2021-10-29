package com.weatherinfo.simpleweatherinfo.dao;

import com.weatherinfo.simpleweatherinfo.domain.City;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CityRepository extends JpaRepository<City, Integer> {
    City findFirstByName(String name);
    City findFirstById(Integer id);
}
