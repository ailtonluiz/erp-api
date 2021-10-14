package com.ailtonluiz.erpapi.domain.service;

import com.ailtonluiz.erpapi.domain.exception.EntityInUseException;
import com.ailtonluiz.erpapi.domain.exception.StateNotFoundException;
import com.ailtonluiz.erpapi.domain.model.State;
import com.ailtonluiz.erpapi.domain.repository.StateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class RegisterStateService {


    private static final String MESSAGE_STATE_IN_USE
            = "Code state %d cannot be removed as it is in use.";

    @Autowired
    private StateRepository stateRepository;

    @Transactional
    public State save(State state) {

        return stateRepository.save(state);
    }

    @Transactional
    public void delete(Long stateId) {
        try {
            stateRepository.deleteById(stateId);
            stateRepository.flush();

        } catch (EmptyResultDataAccessException e) {
            throw new StateNotFoundException(stateId);

        } catch (DataIntegrityViolationException e) {
            throw new EntityInUseException(
                    String.format(MESSAGE_STATE_IN_USE, stateId));
        }
    }
    @Transactional
    public State searchOrFail(Long stateId) {
        return stateRepository.findById(stateId)
                .orElseThrow(() -> new StateNotFoundException(stateId));
    }
}