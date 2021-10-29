package com.weatherinfo.simpleweatherinfo.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Value;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Objects;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.zip.GZIPInputStream;

@Service
public class CityFileServiceImpl implements CityFileService{

    private List<LinkedHashMap<String, Object>> cityList;
    private final Logger logger = LoggerFactory.getLogger(CityFileServiceImpl.class);

    @Value("${openweathermap.filepath}")
    private String filePath;

    public CityFileServiceImpl() {

    }

    @Override
    public LinkedHashMap<String, Object> getCity(String cityName) {
        fillCityList();
        return cityList.stream()
                .filter(item->item.get("name").equals(cityName))
                .findFirst().get();
    }

    @Override
    public LinkedHashMap<String, Object> getCity(Integer id) {
        fillCityList();
        return cityList.stream()
                .filter(compareTo("id", id))
                .findFirst().get();
    }

    private void fillCityList() {
        if (cityList == null) {
            cityList = readAllCities().stream()
                    .map(entry -> (LinkedHashMap<String, Object>) entry)
                    .collect(Collectors.toList());
        }
    }

    private Predicate<LinkedHashMap<String, Object>> compareTo(String fieldName, Integer value) {
        return new Predicate<LinkedHashMap<String, Object>>() {
            @Override
            public boolean test(LinkedHashMap<String, Object> map) {
                if (map.get(fieldName) instanceof Integer) {
                    Integer fieldValue = (Integer) map.get(fieldName);
                    return Objects.equals(value, fieldValue);
                }
                return false;
            }
        };
    }


    public ArrayList<Object> readAllCities() {
        logger.info("City filename is " + filePath);
        return readAllCities(new File(filePath));
    }

    private ArrayList<Object> readAllCities(File file) {
        try(InputStream is = new FileInputStream(file)) {
            return readAllCities(new GZIPInputStream(is));
        } catch(IOException e) {
            logger.warn("File not found");
        }
        return null;
    }

    private ArrayList<Object> readAllCities(InputStream is) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        ArrayList<Object> jsonMap = mapper.readValue(is, ArrayList.class);
        return jsonMap;
    }


}
