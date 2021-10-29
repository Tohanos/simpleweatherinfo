package com.weatherinfo.simpleweatherinfo.service;

import java.util.LinkedHashMap;

public interface CityFileService {
    LinkedHashMap<String, Object> getCity(String cityName);
    LinkedHashMap<String, Object> getCity(Integer id);
}
