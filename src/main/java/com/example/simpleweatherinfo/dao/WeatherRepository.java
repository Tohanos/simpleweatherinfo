package com.example.simpleweatherinfo.dao;

import com.example.simpleweatherinfo.domain.City;
import com.example.simpleweatherinfo.domain.WeatherData;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface WeatherRepository extends JpaRepository<WeatherData, Long> {
    List<WeatherData> findAllByCity(City city);
}
