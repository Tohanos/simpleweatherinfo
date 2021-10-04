package com.example.simpleweatherinfo.scheduler;

import com.example.simpleweatherinfo.service.OpenWeatherMapService;
import com.example.simpleweatherinfo.service.WeatherDatabaseService;
import org.springframework.scheduling.annotation.Scheduled;

public class ScheduledTask {

    private OpenWeatherMapService openWeatherMapService;
    private WeatherDatabaseService weatherDatabaseService;

    @Scheduled(fixedRate = 300000)
    public void readWeather() {
        weatherDatabaseService.saveWeather(openWeatherMapService.getCurrentWeather());
    }
}
