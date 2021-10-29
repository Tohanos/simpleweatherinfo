package com.weatherinfo.simpleweatherinfo.service;

import com.weatherinfo.simpleweatherinfo.dao.CityRepository;
import com.weatherinfo.simpleweatherinfo.domain.City;
import com.weatherinfo.simpleweatherinfo.taskmanager.TaskPool;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Optional;

@Service
public class CityDatabaseServiceImpl implements CityDatabaseService{

    private final CityRepository repository;
    private final CityFileService cityFileService;

    public CityDatabaseServiceImpl(CityRepository repository, CityFileService cityFileService) {
        this.repository = repository;
        this.cityFileService = cityFileService;
    }

    @Override
    @Transactional
    public void saveCity(City city) {
        TaskPool pool = TaskPool.getInstance();
        pool.add(city.getName());
        repository.save(city);
        repository.flush();
    }

    @Override
    public Optional<City> findById(Integer id) {
        Optional<City> city = repository.findById(id);
        if (city.isEmpty()) {
            LinkedHashMap<String, Object> cityHashMap = cityFileService.getCity(id);
            city = Optional.of(buildCity((Integer) cityHashMap.get("id"), (String) cityHashMap.get("name")));
            saveCity(city.get());
        }
        return city;
    }

    @Override
    public Optional<City> findByName(String name) {
        City city = repository.findFirstByName(name);
        if (city == null) {
            LinkedHashMap<String, Object> cityHashMap = cityFileService.getCity(name);
            city = buildCity((Integer) cityHashMap.get("id"), (String) cityHashMap.get("name"));
            if (city != null)
                saveCity(city);
        }
        return Optional.of(city);
    }

    @Override
    public List<City> findAll() {
        return repository.findAll();
    }

    private City buildCity(Integer id, String name) {
        return City.builder()
                .id(id)
                .name(name)
                .build();
    }
}
