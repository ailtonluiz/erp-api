package com.ailtonluiz.erpapi.domain.service;

import com.ailtonluiz.erpapi.domain.exception.CityNotFoundException;
import com.ailtonluiz.erpapi.domain.exception.EntityInUseException;
import com.ailtonluiz.erpapi.domain.model.City;
import com.ailtonluiz.erpapi.domain.model.State;
import com.ailtonluiz.erpapi.domain.repository.CityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class RegisterCityService {

    private static final String MESSAGE_CITY_IN_USE
            = "Code city %d cannot be removed as it is in use";


    @Autowired
    private CityRepository cityRepository;

    @Autowired
    private RegisterStateService registerStateService;

    @Transactional
    public City save(City city) {

        Long stateId = city.getState().getId();

        State state = registerStateService.searchOrFail(stateId);

        city.setState(state);

        return cityRepository.save(city);
    }

    @Transactional
    public void delete(Long cityId) {
        try {
            cityRepository.deleteById(cityId);
            cityRepository.flush();

        } catch (EmptyResultDataAccessException e) {
            throw new CityNotFoundException(cityId);

        } catch (DataIntegrityViolationException e) {
            throw new EntityInUseException(
                    String.format(MESSAGE_CITY_IN_USE, cityId));
        }
    }
    @Transactional
    public City searchOrFail(Long cityId) {
        return cityRepository.findById(cityId)
                .orElseThrow(() -> new CityNotFoundException(cityId));

    }
}