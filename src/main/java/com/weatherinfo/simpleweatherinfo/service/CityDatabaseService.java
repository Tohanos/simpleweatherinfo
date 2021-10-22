package com.weatherinfo.simpleweatherinfo.service;

import com.weatherinfo.simpleweatherinfo.domain.City;

import java.util.Optional;

public interface CityDatabaseService {
    void saveCity(City city);
    Optional<City> findById(Long id);
}
