package com.hmsapp.service;

import com.hmsapp.entity.City;
import com.hmsapp.repository.CityRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CityService {
    private CityRepository cityRepository;

    public CityService(CityRepository cityRepository) {
        this.cityRepository = cityRepository;
    }

    public City addCity(City city) {
        City c = cityRepository.save(city);
        return c;
    }

    public City findCityById(long id) {
        City c = cityRepository.findById(id).orElse(null);
        return c;
    }

    public List<City> getCities() {
        List<City> city = cityRepository.findAll();
        List<City> c = new ArrayList<>(city);
        return c;
    }

    public void deleteCityById(long id) {
        cityRepository.deleteById(id);
    }

    public String updateCity(Long id, City city) {
        Optional<City> existingCity = cityRepository.findById(id);
        if (existingCity.isPresent()) {
            city.setId(id);
            cityRepository.save(city);
            return "City Updated";
        }
        return "No City Found with id " + id;
    }
}
