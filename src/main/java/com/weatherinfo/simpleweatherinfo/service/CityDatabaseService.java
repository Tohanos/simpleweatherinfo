package com.weatherinfo.simpleweatherinfo.service;

import com.weatherinfo.simpleweatherinfo.domain.City;

import java.util.List;
import java.util.Optional;

public interface CityDatabaseService {
    void saveCity(City city);
    Optional<City> findById(Integer id);
    Optional<City> findByName(String name);
    List<City> findAll();
}
