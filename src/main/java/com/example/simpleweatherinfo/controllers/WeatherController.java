package com.example.simpleweatherinfo.controllers;

import com.example.simpleweatherinfo.service.WeatherDatabaseService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class WeatherController {
    private final WeatherDatabaseService weatherDatabaseService;

    public WeatherController(WeatherDatabaseService weatherDatabaseService) {
        this.weatherDatabaseService = weatherDatabaseService;
    }

    @RequestMapping("/{city}/weather")
    public String weatherData(Model model) {
        model.addAttribute("weather", weatherDatabaseService.getWeatherByCity("Saint Petersburg"));
        return "redirect:/index";

    }
}
