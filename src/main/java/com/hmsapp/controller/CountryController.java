package com.hmsapp.controller;

import com.hmsapp.entity.Country;
import com.hmsapp.service.CountryService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/country")
public class CountryController {
    private CountryService countryService;

    public CountryController(CountryService countryService) {
        this.countryService = countryService;
    }

    @PostMapping("/addCountry")
    public ResponseEntity<?> addCountry(@RequestBody Country country) {
        Country c = countryService.addCountry(country);
        return new ResponseEntity<>(c, HttpStatus.OK);
    }

    @GetMapping("/{countryId}")
    public ResponseEntity<Country> findCountryById(@PathVariable long countryId) {
        Country c = countryService.findCountryById(countryId);
        return new ResponseEntity<>(c, HttpStatus.OK);
    }

    @GetMapping("/allCountry")
    public ResponseEntity<List<Country>> getAllCountries(){
        List<Country> countryList = countryService.getCountries();
        return new ResponseEntity<>(countryList, HttpStatus.OK);
    }

    @DeleteMapping("/deleteCountryById/{countryId}")
    public ResponseEntity<?> deleteCountryById(@PathVariable long countryId) {
        countryService.deleteCountryById(countryId);
        return new ResponseEntity<>("Country Deleted", HttpStatus.OK);
    }

    @PutMapping("/updateCountry/{countryId}")
    public ResponseEntity<String> updateCountry(@PathVariable long countryId, @RequestBody Country country) {
        String result= countryService.updateCountry(countryId, country);
        if(result.equals("Country Updated")) {
            return new ResponseEntity<>(result, HttpStatus.OK);
        }else {
            return new ResponseEntity<>(result, HttpStatus.INTERNAL_SERVER_ERROR); // HTTP 500 for server error
        }
    }
}
