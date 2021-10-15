package com.ailtonluiz.erpapi.domain.service;

import com.ailtonluiz.erpapi.domain.exception.EntityInUseException;
import com.ailtonluiz.erpapi.domain.exception.StateNotFoundException;
import com.ailtonluiz.erpapi.domain.exception.UnitNotFoundException;
import com.ailtonluiz.erpapi.domain.model.State;
import com.ailtonluiz.erpapi.domain.model.Unit;
import com.ailtonluiz.erpapi.domain.repository.StateRepository;
import com.ailtonluiz.erpapi.domain.repository.UnitRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class RegisterUnitService {


    private static final String MESSAGE_UNIT_IN_USE
            = "Code Unit %d cannot be removed as it is in use.";

    @Autowired
    private UnitRepository unitRepository;

    @Transactional
    public Unit save(Unit unit) {

        return unitRepository.save(unit);
    }

    @Transactional
    public void delete(Long unitId) {
        try {
            unitRepository.deleteById(unitId);
            unitRepository.flush();

        } catch (EmptyResultDataAccessException e) {
            throw new UnitNotFoundException(unitId);

        } catch (DataIntegrityViolationException e) {
            throw new EntityInUseException(
                    String.format(MESSAGE_UNIT_IN_USE, unitId));
        }
    }

    @Transactional
    public Unit searchOrFail(Long unitId) {
        return unitRepository.findById(unitId)
                .orElseThrow(() -> new UnitNotFoundException(unitId));
    }
}