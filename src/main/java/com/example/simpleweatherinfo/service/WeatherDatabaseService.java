package com.example.simpleweatherinfo.service;

import com.example.simpleweatherinfo.dto.WeatherDataDto;
import com.github.prominence.openweathermap.api.model.weather.Weather;

import java.util.List;

public interface WeatherDatabaseService {
    void saveWeather(Weather weather);
    List<WeatherDataDto> getWeatherByCity(String cityName);
}
