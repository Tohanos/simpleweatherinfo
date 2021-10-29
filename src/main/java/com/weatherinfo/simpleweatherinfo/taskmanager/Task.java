package com.weatherinfo.simpleweatherinfo.taskmanager;

import com.weatherinfo.simpleweatherinfo.service.OpenWeatherMapService;
import com.weatherinfo.simpleweatherinfo.service.WeatherDatabaseService;
import lombok.SneakyThrows;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.TimeUnit;

public class Task implements Runnable{

    private final OpenWeatherMapService openWeatherMapService;
    private final WeatherDatabaseService weatherDatabaseService;

    public Task(OpenWeatherMapService openWeatherMapService, WeatherDatabaseService weatherDatabaseService) {
        this.openWeatherMapService = openWeatherMapService;
        this.weatherDatabaseService = weatherDatabaseService;
    }

    private final Logger logger = LoggerFactory.getLogger(Task.class);

    @SneakyThrows
    @Override
    public void run() {
        TaskPool pool = TaskPool.getInstance();
        String location = pool.getNext();
        weatherDatabaseService.saveWeather(openWeatherMapService.getCurrentWeather(location));
        logger.info("Executed scheduled task for location " + location);
        TimeUnit.SECONDS.sleep(1);
    }

}
