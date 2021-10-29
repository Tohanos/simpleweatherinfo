package com.weatherinfo.simpleweatherinfo.scheduler;

import com.weatherinfo.simpleweatherinfo.domain.City;
import com.weatherinfo.simpleweatherinfo.service.CityDatabaseService;
import com.weatherinfo.simpleweatherinfo.service.OpenWeatherMapService;
import com.weatherinfo.simpleweatherinfo.service.WeatherDatabaseService;
import com.weatherinfo.simpleweatherinfo.taskmanager.TaskManager;
import com.weatherinfo.simpleweatherinfo.taskmanager.TaskPool;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ScheduledTask {

    private final OpenWeatherMapService openWeatherMapService;
    private final WeatherDatabaseService weatherDatabaseService;
    private final CityDatabaseService cityDatabaseService;
    private final TaskPool pool;
    private final TaskManager taskManager;

    @Value("${openweathermap.location}")
    private String defaultLocation;

    private final Logger logger = LoggerFactory.getLogger(ScheduledTask.class);

    public ScheduledTask(OpenWeatherMapService openWeatherMapService, WeatherDatabaseService weatherDatabaseService, CityDatabaseService cityDatabaseService, TaskManager taskManager) {
        this.openWeatherMapService = openWeatherMapService;
        this.weatherDatabaseService = weatherDatabaseService;
        this.cityDatabaseService = cityDatabaseService;
        this.taskManager = taskManager;
        pool = TaskPool.getInstance();
    }

    @Scheduled(fixedRate = 600000, initialDelay = 5000)
    public void readWeather() {
        pool.setDefaultLocation(defaultLocation);
        List<City> cities = cityDatabaseService.findAll();
        cities.forEach(city -> pool.add(city.getName()));
        logger.info("Scheduled task executed");
        taskManager.executeSequence();

    }
}
