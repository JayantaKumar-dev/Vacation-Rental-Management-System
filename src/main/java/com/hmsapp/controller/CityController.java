package com.hmsapp.controller;

import com.hmsapp.entity.City;
import com.hmsapp.service.CityService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/city")
public class CityController {
    private CityService cityService;

    public CityController(CityService cityService) {
        this.cityService = cityService;
    }

    @PostMapping("/addCity")
    public ResponseEntity<?> addCity(@RequestBody City city) {
        City c = cityService.addCity(city);
        return new ResponseEntity<>(c, HttpStatus.OK);
    }

    @GetMapping("/{cityId}")
    public ResponseEntity<City> findCityById(@PathVariable long cityId) {
        City c = cityService.findCityById(cityId);
        return new ResponseEntity<>(c, HttpStatus.OK);
    }

    @GetMapping("/allCity")
    public ResponseEntity<List<City>> getAllCities(){
        List<City> cityList = cityService.getCities();
        return new ResponseEntity<>(cityList, HttpStatus.OK);
    }

    @DeleteMapping("/deleteCityById/{cityId}")
    public ResponseEntity<?> deleteCityById(@PathVariable long cityId) {
        cityService.deleteCityById(cityId);
        return new ResponseEntity<>("City Deleted", HttpStatus.OK);
    }

    @PutMapping("/updateCity/{cityId}")
    public ResponseEntity<String> updateCity(@PathVariable long cityId, @RequestBody City city) {
        String result= cityService.updateCity(cityId, city);
        if(result.equals("City Updated")) {
            return new ResponseEntity<>(result, HttpStatus.OK);
        }else {
            return new ResponseEntity<>(result, HttpStatus.INTERNAL_SERVER_ERROR); // HTTP 500 for server error
        }
    }
}
