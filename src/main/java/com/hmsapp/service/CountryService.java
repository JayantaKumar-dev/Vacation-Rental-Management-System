package com.hmsapp.service;

import com.hmsapp.entity.Country;
import com.hmsapp.repository.CountryRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CountryService {
    private CountryRepository countryRepository;

    public CountryService(CountryRepository countryRepository) {
        this.countryRepository = countryRepository;
    }

    public Country addCountry(Country country) {
        Country c = countryRepository.save(country);
        return c;
    }

    public Country findCountryById(long id) {
        Country c = countryRepository.findById(id).orElse(null);
        return c;
    }

    public List<Country> getCountries() {
        List<Country> country = countryRepository.findAll();
        List<Country> c = new ArrayList<>(country);
        return c;
    }

    public void deleteCountryById(long id) {
        countryRepository.deleteById(id);
    }

    public String updateCountry(Long id, Country country) {
        Optional<Country> existingCountry = countryRepository.findById(id);
        if (existingCountry.isPresent()) {
            country.setId(id);
            countryRepository.save(country);
            return "Country Updated";
        }
        return "No Country Found with id " + id;
    }
}
