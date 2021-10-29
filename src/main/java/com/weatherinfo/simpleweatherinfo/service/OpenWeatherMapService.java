package com.weatherinfo.simpleweatherinfo.service;

import com.github.prominence.openweathermap.api.OpenWeatherMapClient;
import com.github.prominence.openweathermap.api.enums.Language;
import com.github.prominence.openweathermap.api.enums.UnitSystem;
import com.github.prominence.openweathermap.api.model.weather.Weather;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class OpenWeatherMapService {
    private OpenWeatherMapClient client;

    @Value("${openweathermap.apikey}")
    private String apiKey;

    public Weather getCurrentWeather(String location) { //TODO заменить String на City
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
