package com.weatherinfo.simpleweatherinfo.dao;

import com.weatherinfo.simpleweatherinfo.domain.City;
import com.weatherinfo.simpleweatherinfo.domain.WeatherData;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface WeatherRepository extends JpaRepository<WeatherData, Long> {
    List<WeatherData> findAllByCity(City city);
}
