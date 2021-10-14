package com.ailtonluiz.erpapi.api.controller;

import com.ailtonluiz.erpapi.domain.model.City;
import com.ailtonluiz.erpapi.domain.repository.CityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/cities")
public class CityController {

    @Autowired
    private CityRepository cityRepository;


    @GetMapping
    public List<City> list() {

        List<City> allCitys = cityRepository.findAll();

        return allCitys;

    }
}
