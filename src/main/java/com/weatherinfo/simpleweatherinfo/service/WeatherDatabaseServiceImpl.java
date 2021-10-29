package com.weatherinfo.simpleweatherinfo.service;

import com.weatherinfo.simpleweatherinfo.dao.WeatherRepository;
import com.weatherinfo.simpleweatherinfo.domain.City;
import com.weatherinfo.simpleweatherinfo.domain.WeatherData;
import com.weatherinfo.simpleweatherinfo.dto.WeatherDataDto;
import com.github.prominence.openweathermap.api.model.weather.Weather;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@Service
public class WeatherDatabaseServiceImpl implements WeatherDatabaseService{

    private final WeatherRepository weatherRepository;
    private final CityDatabaseService cityDatabaseService;

    public WeatherDatabaseServiceImpl(WeatherRepository weatherRepository, CityDatabaseService cityDatabaseService) {
        this.weatherRepository = weatherRepository;
        this.cityDatabaseService = cityDatabaseService;
    }

    @Override
    @Transactional
    public void saveWeather(Weather weather) {
        City city = cityDatabaseService.findById(weather.getLocation().getId()).get();

        WeatherData weatherData = WeatherData.builder()
                .city(city)
                .temperature(weather.getTemperature().getValue())
                .timestamp(Timestamp.from(Instant.now()))
                .build();
        weatherRepository.save(weatherData);
    }

    @Override
    public List<WeatherDataDto> getWeatherByCity(String cityName) {

        City city = cityDatabaseService.findByName(cityName).get(); //TODO Add default city if Optional.get returns null
        return fromCity(city);
    }

    @Override
    public List<WeatherDataDto> getWeatherByCity(Integer id) {
        City city = cityDatabaseService.findById(id).get(); //TODO Add default city if Optional.get returns null
        return fromCity(city);
    }

    private List<WeatherDataDto> fromCity(City city) {
        List<WeatherDataDto> weatherDataDtoList = new ArrayList<>();
        List<WeatherData> weatherDataList = weatherRepository.findAllByCity(city);
        for (WeatherData weatherData:
                weatherDataList) {
            weatherDataDtoList.add(new WeatherDataDto(weatherData));
        }
        return weatherDataDtoList;
    }
}
