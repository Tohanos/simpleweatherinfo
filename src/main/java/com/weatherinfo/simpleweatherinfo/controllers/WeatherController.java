package com.weatherinfo.simpleweatherinfo.controllers;

import com.weatherinfo.simpleweatherinfo.service.WeatherDatabaseService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class WeatherController {
    private final WeatherDatabaseService weatherDatabaseService;

    public WeatherController(WeatherDatabaseService weatherDatabaseService) {
        this.weatherDatabaseService = weatherDatabaseService;
    }

    @RequestMapping("/{city}/weather")
    public String weatherData(@PathVariable("city")String city, Model model) {
        model.addAttribute("weatherTemperatures", weatherDatabaseService.getWeatherByCity(city));
        return "redirect:/index";

    }
}
