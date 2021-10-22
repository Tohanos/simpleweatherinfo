package com.weatherinfo.simpleweatherinfo.service;

import com.weatherinfo.simpleweatherinfo.dao.CityRepository;
import com.weatherinfo.simpleweatherinfo.dao.WeatherRepository;
import com.weatherinfo.simpleweatherinfo.domain.City;
import com.weatherinfo.simpleweatherinfo.domain.WeatherData;
import com.weatherinfo.simpleweatherinfo.dto.WeatherDataDto;
import com.github.prominence.openweathermap.api.model.weather.Weather;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class WeatherDatabaseServiceImpl implements WeatherDatabaseService{

    private CityRepository cityRepository;
    private WeatherRepository weatherRepository;

    @Override
    public void saveWeather(Weather weather) {
        City city = cityRepository.findFirstByName(weather.getLocation().getName());
        WeatherData weatherData = WeatherData.builder()
                .city(city)
                .temperature(weather.getTemperature().getValue())
                .build();
        weatherRepository.save(weatherData);
    }

    @Override
    public List<WeatherDataDto> getWeatherByCity(String cityName) {
        City city = cityRepository.findFirstByName(cityName);
        List<WeatherDataDto> weatherDataDtoList = new ArrayList<>();
        List<WeatherData> weatherDataList = weatherRepository.findAllByCity(city);
        for (WeatherData weatherData:
             weatherDataList) {
            weatherDataDtoList.add(new WeatherDataDto(weatherData));
        }
        return weatherDataDtoList;
    }
}
