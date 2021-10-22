package com.weatherinfo.simpleweatherinfo.dto;

import com.weatherinfo.simpleweatherinfo.domain.WeatherData;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class WeatherDataDto {
    private String city;
    private String timestamp;
    private String temperature;

    public WeatherDataDto(WeatherData weatherData) {
        this.city = weatherData.getCity().getName();
        this.timestamp = weatherData.getTimestamp().toString();
        this.temperature = Double.toString(weatherData.getTemperature());
    }
}
