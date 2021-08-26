package com.example.simpleweatherinfo.service;

import com.github.prominence.openweathermap.api.OpenWeatherMapClient;
import com.github.prominence.openweathermap.api.enums.Language;
import com.github.prominence.openweathermap.api.enums.UnitSystem;
import com.github.prominence.openweathermap.api.model.weather.Weather;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor

public class OpenWeatherMapService {
    private OpenWeatherMapClient client;

    private String apiKey;
    private String location;

    public Weather getCurrentWeather() {
        if (client == null) {
            client = new OpenWeatherMapClient(apiKey);
        }
        return client
                .currentWeather()
                .single()
                .byCityName(location)
                .language(Language.RUSSIAN)
                .unitSystem(UnitSystem.METRIC)
                .retrieve()
                .asJava();
    }
}
