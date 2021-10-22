package com.weatherinfo.simpleweatherinfo.service;

import com.weatherinfo.simpleweatherinfo.dao.CityRepository;
import com.weatherinfo.simpleweatherinfo.domain.City;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CityDatabaseServiceImpl implements CityDatabaseService{

    private final CityRepository repository;

    public CityDatabaseServiceImpl(CityRepository repository) {
        this.repository = repository;
    }

    @Override
    public void saveCity(City city) {
        repository.save(city);
    }

    @Override
    public Optional<City> findById(Long id) {
        return repository.findById(id);
    }
}
