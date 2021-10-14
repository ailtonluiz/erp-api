package com.ailtonluiz.erpapi.api.controller;

import com.ailtonluiz.erpapi.api.assembler.CityModelAssembler;
import com.ailtonluiz.erpapi.api.disassembler.CityInputDisassembler;
import com.ailtonluiz.erpapi.api.model.CityModel;
import com.ailtonluiz.erpapi.domain.model.City;
import com.ailtonluiz.erpapi.domain.repository.CityRepository;
import com.ailtonluiz.erpapi.domain.service.RegisterCityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/cities")
public class CityController {

    @Autowired
    private CityRepository cityRepository;

    @Autowired
    private RegisterCityService registerCityService;

    @Autowired
    private CityModelAssembler cityModelAssembler;

    @Autowired
    private CityInputDisassembler cityInputDisassembler;


    @GetMapping
    public List<CityModel> list() {
        List<City> allCitys = cityRepository.findAll();

        return cityModelAssembler.toCollectionModel(allCitys);
    }

    @GetMapping("/{cityId}")
    public CityModel search(@PathVariable Long cityId) {
        City city = registerCityService.searchOrFail(cityId);

        return cityModelAssembler.toModel(city);
    }
}
