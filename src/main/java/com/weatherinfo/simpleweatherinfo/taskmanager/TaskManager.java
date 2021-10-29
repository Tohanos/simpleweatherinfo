package com.weatherinfo.simpleweatherinfo.taskmanager;

import com.weatherinfo.simpleweatherinfo.scheduler.ScheduledTask;
import com.weatherinfo.simpleweatherinfo.service.OpenWeatherMapService;
import com.weatherinfo.simpleweatherinfo.service.WeatherDatabaseService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

@Component
public class TaskManager {
    private final OpenWeatherMapService openWeatherMapService;
    private final WeatherDatabaseService weatherDatabaseService;

    private final Logger logger = LoggerFactory.getLogger(TaskManager.class);

    public TaskManager(OpenWeatherMapService openWeatherMapService, WeatherDatabaseService weatherDatabaseService) {
        this.openWeatherMapService = openWeatherMapService;
        this.weatherDatabaseService = weatherDatabaseService;
    }

    public void executeSequence() {
        ExecutorService service = Executors.newFixedThreadPool(1);
        TaskPool pool = TaskPool.getInstance();
        logger.info("Executor started");
        for (int i = 0; i < pool.getSize(); i++) {
            service.execute(new Task(openWeatherMapService, weatherDatabaseService));
        }
    }
}
